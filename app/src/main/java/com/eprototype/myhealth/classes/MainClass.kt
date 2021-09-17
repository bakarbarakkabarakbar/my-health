package com.eprototype.myhealth.classes

data class MainClass(var uid: String = "",
                     val user: UserClass,
                     val auth: AuthClass,
                     val health_record: HealthRecordClass)
