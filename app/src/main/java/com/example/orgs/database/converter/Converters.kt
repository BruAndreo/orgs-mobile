package com.example.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromDouble(valor: Double?) : BigDecimal {
        return valor?.let { BigDecimal(it.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalToDouble(valor: BigDecimal?) : Double? {
        return valor?.let { it.toDouble() }
    }

}