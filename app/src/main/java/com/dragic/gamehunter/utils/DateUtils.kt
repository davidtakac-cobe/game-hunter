package com.dragic.gamehunter.utils

import java.text.SimpleDateFormat
import java.util.Locale

private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)

fun getDateString(time: Int): String = simpleDateFormat.format(time * 1000L)
