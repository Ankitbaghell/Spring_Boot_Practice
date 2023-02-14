package com.example.demoproject.model.entity

import javax.persistence.*

@Entity
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = -1,
    var name: String = "",
    var price: Double =0.0
) {
//    constructor(
//        var name: String,
//        var price: Double
//    )
}



//class ProductEntity {
//    constructor() {}
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    var id =0
//
//    @Column(name = "name")
//    var name: String =""
//
//    @Column(name = "price")
//     var price = 0
//}
//data class ProductEntity(
//    val name: String,
//    val price: Double,
//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//    val id: Long = -1) {
//    //constructor() : this("",0.0)
//}

//Data class provides you with everything, getters, setters, hashCode,
// toString and equals functions.
// So all you have to do is create an instance and start using the functions.