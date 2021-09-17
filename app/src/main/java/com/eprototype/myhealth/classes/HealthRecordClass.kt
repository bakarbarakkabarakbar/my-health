package com.eprototype.myhealth.classes

import android.provider.DocumentsContract
import java.time.LocalDate

open class HealthRecordClass(val uid: String = "",
                             var name: String = "",
                             var datecreated: LocalDate? = null,
                             var lastmodified: LocalDate? = null,
                             var note: String = "",
                             var document: DocumentsContract.Document)
