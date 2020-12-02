package models;

import org.sql2o.Sql2o;

public class DB {

    //public static Sql2o sql2o = new Sql2o( "jdbc:postgresql://localhost:5432/faunatailer", "odile", "123");
    public static Sql2o sql2o = new Sql2o( "jdbc:postgresql://ec2-54-237-135-248.compute-1.amazonaws.com:5432/dcnhd9b7ukgv4i", "dnmnmclytjrzjd", "e598c69fe2b7345f8d835d57fb9b0db1c29557fe458996c64e104b468a5e9067");


}
