package com.sctgaming.dungeoncrawl.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PropertyHolder implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * Gets a property from this specific unit.<br>
	 * <br>
	 * Typical usage is: <br>
	 * <code>int health = unit.getProperty(Property.HEALTH);</code><br>
	 * <br>
	 * Properties are defined in {@link Properties}<br>
	 * 
	 * @param key {@link Property} used to define what property you are getting
	 * @return value of the property, or the default value of the property if the property was not found.
	 */
	public <T extends Serializable> T getProperty(Property<T> key) {
		Object obj = properties.get(key.getKey());
		if (obj == null) {
			return key.getDefValue();
		}

		if (key.getVClass().isInstance(obj)) {
			return key.getVClass().cast(obj);
		} else {
			return key.getDefValue();
		}
	}

	/**
	 * Sets a property for this specific unit.<br>
	 * <br>
	 * Typical usage is: <br>
	 * <code>unit.setProperty(Property.HEALTH, 5);</code><br>
	 * <br>
	 * Properties are defined in {@link Properties}<br>
	 * 
	 * @param key {@link Property} used to define what property you are setting
	 * @param value of the property you want to set
	 * @return previous value of the property, or null if the property did not have a value already
	 */
	public <T extends Serializable> T setProperty(Property<T> key, T value) {
		Object obj = properties.put(key.getKey(), value);
		if (obj == null) {
			return null;
		}

		T oldVal = null;
		if (key.getVClass().isInstance(obj)) {
			oldVal = key.getVClass().cast(obj);
		}
		return oldVal;
	}
}
