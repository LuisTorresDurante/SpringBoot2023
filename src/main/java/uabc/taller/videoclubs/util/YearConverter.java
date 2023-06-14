package uabc.taller.videoclubs.util;

import java.time.Year;

import javax.persistence.AttributeConverter;

public abstract class YearConverter implements AttributeConverter<Year, Short> {
	@Override
	public Short convertToDatabaseColumn(Year year) {
		if(year==null) {
			return null;
		}
		return (short) year.getValue()
	}
	
	@Override
	public year convertToEntityAttribute(short isoYear) {
		if(isoYear==null) {
			return null;
		}
		return Year.of(isoYear);
	}
}
