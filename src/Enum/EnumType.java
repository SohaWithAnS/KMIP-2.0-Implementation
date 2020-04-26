package Enum;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.*;

/**
 * EnumType class encompasses mapping the various types to their subsequent tags
 * as specified by KMIP for TTLV encoding.
 * @author Vedika Ghei, Soha Parasnis, Tanisha Rathi, Vidushi Mishra 
 * @version 1.0
 * @see KMIPEnum
 */
//TTLV types
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EnumType extends KMIPEnum {
	
	public static final int Integer= 0x02;
	public static final int LongInteger= 0x03;
	public static final int BigInteger= 0x04;
	public static final int Enumeration= 0x05;
	public static final int Boolean= 0x06;
	public static final int TextString= 0x07;
	public static final int ByteString= 0x08;
	public static final int DateTime= 0x09;
	public static final int Interval= 0x0A;
	public static HashMap<String, Integer> tagvalues;
	
	String type;

	static {
		tagvalues=new HashMap<String, Integer>();
		Field tagfieldvalues[]=EnumType.class.getDeclaredFields();
		for(Field f: tagfieldvalues)
		{
			if(Modifier.isStatic(f.getModifiers())&&Modifier.isFinal(f.getModifiers()))
			{
				try {
					tagvalues.put(f.getName(), f.getInt(EnumType.class));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Parameterized constructor.
	 * @param value
	 */
	public EnumType(int value)
	{
		this.value=getEntry(value, tagvalues);
		//System.out.println("EnumType : "+value);
	}

	/**
	 * Getter.
	 * @return String the tag value (value) is used to find the type (key) and the type is returned.
	 */
	public String getType()
	{
		this.type = this.value.getKey();
		return this.type;
	}

	/**
	 * Setter.
	 * @param type KMIP type.
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Default constructor.
	 */
	public EnumType()
	{

	}

}
