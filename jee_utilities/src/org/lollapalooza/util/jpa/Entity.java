package org.lollapalooza.util.jpa;

public abstract class Entity {
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public static boolean isId(Integer id) {
        return (id != null && id > 0);
    }
 
    public boolean hasId() {
        return isId(getId());
    }
 
    public abstract Integer getId();
}
