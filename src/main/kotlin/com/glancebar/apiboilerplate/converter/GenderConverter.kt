package com.glancebar.apiboilerplate.converter

import com.glancebar.apiboilerplate.entity.GenderEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

/**
 * @ref https://stackoverflow.com/questions/39079608/spring-data-mongodb-enum-mapping-converter
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Component
class GenderConverter : Converter<String, GenderEnum> {

    override fun convert(p0: String): GenderEnum? {
        for (gender in GenderEnum.values()) {
            if (gender.value == p0) {
                return gender
            }
        }
        return null
    }
}