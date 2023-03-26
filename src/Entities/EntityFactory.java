package Entities;

import Entities.Statics.DeathLine;
import Entities.Statics.Lava;
import Entities.Statics.Nothing;
import Entities.Statics.Rock;
import Game.Handler;

public class EntityFactory {

    private Handler handler;

    public EntityFactory(Handler handler){
        this.handler = handler;
    }

    public Entity Produce(int id, float x, float y)
    {
        return switch (id){
            case 6 -> new DeathLine(handler, x, y);
            case 7 -> new Rock(handler, x, y);
            case 8 -> new Lava(handler, x, y);
            default -> new Nothing(handler, x, y);
        };
    }
}
