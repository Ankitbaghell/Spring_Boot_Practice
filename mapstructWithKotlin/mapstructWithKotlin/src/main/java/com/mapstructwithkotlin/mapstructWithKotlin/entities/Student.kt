package com.mapstructwithkotlin.mapstructWithKotlin.entities;

data class Student internal constructor(val name :String?, val id :Int?, val age:Int?){
    data class Builder(
        var name : String  = "Tester",
        var id: Int? = null,
        var age: Int?=null
    ){
        fun name(name: String) = apply { this.name = name }
        fun id(id: Int) = apply { this.id  = id}
        fun age(age: Int) = apply { this.age  = age}
        fun build() = Student(name, id, age)
    }
}

fun main(){
    var student = Student.Builder(name = "thus", age = 5).build();
    var student2 = Student.Builder().name("sumit").build();
    var student3 = Student.Builder().id(1).age(5).build();
    println(student)
    println(student2)
    println(student3)
}




//data class Student(val name: String, val id: Int) {
//    data class Builder(var name: String = "", var id: Int = 0) {
//        fun name(name: String) = apply { this.name = name }
//        fun id(id: Int) = apply { this.id = id }
//        fun build() = Student(name, id)
//    }
//    companion object {
//        fun builder() = Builder()
//    }
//}


//class FoodOrder private constructor(
//    val bread: String?,
//    val condiments: String?,
//    val meat: String?,
//    val fish: String?) {
//
//    data class Builder(
//        var bread: String? = null,
//        var condiments: String? = null,
//        var meat: String? = null,
//        var fish: String? = null) {
//
//        fun bread(bread: String) = apply { this.bread = bread }
//        fun condiments(condiments: String) = apply { this.condiments = condiments }
//        fun meat(meat: String) = apply { this.meat = meat }
//        fun fish(fish: String) = apply { this.fish = fish }
//        fun build() = FoodOrder(bread, condiments, meat, fish)
//    }
//}