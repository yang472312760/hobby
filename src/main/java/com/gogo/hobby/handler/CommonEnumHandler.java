package com.gogo.hobby.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

import com.gogo.hobby.enums.IBaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author yang
 * @Description 通用枚举类型处理器
 * @date 2017-4-29 上午9:39:59
 */
public class CommonEnumHandler<E extends Enum<E> & IBaseEnum<K>, K> extends BaseTypeHandler<E> {

	private Class<E> type;

	public CommonEnumHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException ("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String s = rs.getString (columnName);
		return toEnum (s);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String s = rs.getString (columnIndex);
		return toEnum (s);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String s = cs.getString (columnIndex);
		return toEnum (s);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		if (jdbcType == null) {
			K id = parameter.getIdentity ();
			if (id instanceof Integer || id instanceof Short || id instanceof Character || id instanceof Byte) {
				ps.setInt (i, (Integer) id);
			} else if (id instanceof String) {
				ps.setString (i, (String) id);
			} else if (id instanceof Boolean) {
				ps.setBoolean (i, (Boolean) id);
			} else if (id instanceof Long) {
				ps.setLong (i, (Long) id);
			} else if (id instanceof Double) {
				ps.setDouble (i, (Double) id);
			} else if (id instanceof Float) {
				ps.setFloat (i, (Float) id);
			} else {
				throw new RuntimeException ("unsupported [id] type of enum");
			}
		} else {
			ps.setObject (i, parameter.getIdentity (), jdbcType.TYPE_CODE);
		}
	}

	private E toEnum(String id) {
		EnumSet<E> set = EnumSet.allOf (type);
		if (set == null || set.size () <= 0) {
			return null;
		}
		for (E e : set) {
			K k = e.getIdentity ();
			if (k != null) {
				if (k.toString ().equals (id)) {
					return e;
				}
			}
		}
		return null;
	}

}
