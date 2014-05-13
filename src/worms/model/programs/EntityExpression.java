package worms.model.programs;

import worms.model.Entity;

public abstract class EntityExpression implements Expression<Entity> {
	
	@Override
	public abstract Entity getExpression();
	
	@Override
    public final Class<Entity> getType() {
        return Entity.class;
    }
}
