package com.example.springboottest.dao;

import com.example.springboottest.data.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

import com.example.springboottest.data.Ingredient;

@Repository
public class TacoDaoImpl extends BaseDao implements TacoDao {

    private JdbcTemplate jdbc;

    @Autowired
    public TacoDaoImpl(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId=saveTacoInfo(taco);
        taco.setId(tacoId);
//        for (String str:taco.getIngredients()){
//            Ingredient ingredient=new Ingredient(str,null,null);
//            saveIngredientToTaco(ingredient,tacoId);
//        }
        for (Ingredient ingredient:taco.getIngredients()){
            saveIngredientToTaco(ingredient,tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        //
        PreparedStatementCreatorFactory pscf=new PreparedStatementCreatorFactory(
                "insert into Taco(name,createdAt) values (?,?)",
                Types.VARCHAR,Types.TIMESTAMP);
        //使用mysql时需要手动设置返回构造的key
        pscf.setReturnGeneratedKeys(true);
        //
        PreparedStatementCreator psc=pscf
                .newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);
        return keyHolder.getKey().longValue();

//        jdbc.update(psc);
//        System.out.println("TacoDaoImpl.saveTacoInfo" + "----taco.getCreatedAt().toString() 值= " + taco.getCreatedAt().toString());
//        Taco taco1= jdbc.queryForObject("select * from Taco where name=? and createdAt=?",
//                this::mapRowToTaco,
////                "Nodejs全栈开发",
//                taco.getName(),
////                "2022-07-22 15:58:20"
//                taco.getCreatedAt().toString()
//        );
//        if(taco1!=null)
//            return taco1.getId();
//        else return -1;
    }

//    private Taco mapRowToTaco(ResultSet rs, int rowNum) throws SQLException {
//        return new Taco(
//                rs.getLong("id"),
//                rs.getDate("createdAt"),
//                rs.getString("name"),
//                null);
//    }


    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients(taco,ingredient) values (?,?)",
                tacoId,ingredient.getId()
        );
    }
}
