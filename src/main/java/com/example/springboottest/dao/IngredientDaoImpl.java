package com.example.springboottest.dao;

import com.example.springboottest.data.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientDaoImpl extends BaseDao implements IngredientDao {

    private JdbcTemplate jdbc;

    @Autowired
    public IngredientDaoImpl(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient(id,name,type) values (?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    public Ingredient findOne(String id){
        //lambda对象方法引用
//        IngredientDaoImpl ingredientDao=new IngredientDaoImpl(jdbc);
//        RowMapper mapRow=ingredientDao::mapRowToIngredient;

        return jdbc.queryForObject("select id,name,type from Ingredient where id=?",
                this::mapRowToIngredient,id);
    }

    //lambda表达式、对象方法引用 https://cloud.tencent.com/developer/article/1532870
    private Ingredient mapRowToIngredient(ResultSet rs,int rowNum) throws SQLException{
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

//    @Override  jdbc原生查询
    public Ingredient findOne1(String id){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection=getConnection();
            statement=connection.prepareStatement("select  id,name ,type from Ingredient where id=?");
            statement.setString(1,id);
            resultSet=statement.executeQuery();
            Ingredient ingredient=null;
            if(resultSet.next()){
                ingredient=new Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Type.valueOf(resultSet.getString("type")));
            }
            return ingredient;
        }catch (SQLException sqlException){
            System.out.println("IngredientDao.findOne" + "----sqlException 值= " + sqlException);
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
