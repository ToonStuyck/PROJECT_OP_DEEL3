package worms.model.programs.Expression;

import worms.model.programs.Type.EntityType;

public class EntityExpression<T> extends PartExpression{

	public EntityExpression(EntityType<T> entity) {
		this.entity = entity;
	}
	
	@Override
	public EntityType<T> getValue() {
		return this.entity;
	}

	public final EntityType<T> entity;
}
