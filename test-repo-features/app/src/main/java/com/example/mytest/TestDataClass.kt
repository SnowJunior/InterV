package com.example.mytest

val testDataList : MutableList<TestDataClass> = mutableListOf()

data class TestDataClass(
    val name : String,
    val age : String,
    val iq : String,
    val country : String = "Kenya",
    val gender : Boolean, // true = Male, false = female
    val maritalStatus : Boolean // married = true, single = false
) {

    var TotalOdds = 0;

    init {
        if(gender == false) {
            TotalOdds = 56;
        }
    }
}
