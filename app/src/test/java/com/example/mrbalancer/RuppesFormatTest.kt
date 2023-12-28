package com.example.mrbalancer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mrbalancer.Util.RupessFormat
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith



class RuppesFormatTest {

    @Test
    fun ruppes_NUll()
    {
        val str=-12;
        val result=RupessFormat.rupessConverter(str)
        assertThat(result).matches("12")
    }

}