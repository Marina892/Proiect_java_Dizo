package States.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static BazaDeDate.BazaDeDate.GetLevelPathDiamondsFromDataBase;
import static BazaDeDate.BazaDeDate.GetLevelPathFromDataBase;
import static States.Util.Levels.levels.level2;
import static States.Util.Levels.levels.level4;

public class Levels {

    public enum levels {
        level1, level2, level3, level4;
    }

    public static levels nextLevel(levels level)
    {
        switch (level)
        {
            case level1:
            default:
                return level2;
        }
    }

    public static levels nextLevelDiamant(levels level)
    {
        switch (level){
            case level3:
            default:
                return level4;
        }
    }

    public static String GetLevelNme(levels level)
    {
        switch (level){
            case level1:
                return "Nivelul 1";
            default:
                return "Nivelul 2";
        }
    }

    public static String GetLevelWorld(levels level)
    {
        switch (level){
            case level1:
                //return "resource/Levels/Level1.txt";
                return GetLevelPathFromDataBase(1);
            case level2:
            default:
                //return "resource/Levels/Level2.txt";
                return GetLevelPathFromDataBase(2);
        }
    }

    public static String GetLevelWorldDiamant(levels level)
    {
        switch (level){
            case level3:
                //return "resource/Levels/Level1Diamante.txt";
                return GetLevelPathDiamondsFromDataBase(1);
            case level4:
            default:
                //return "resource/Levels/Level2DMG.txt";
                return GetLevelPathDiamondsFromDataBase(2);
        }
    }

}
