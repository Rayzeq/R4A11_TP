package com.example.tp2.controller

import java.util.Calendar

class AgeCalculator {
    fun calculateAge(birthYear: Int): Int {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - birthYear;
    }
}