package com.eprototype.myhealth.classes

import java.time.LocalDate

open class UserClass(var nik: Int = -1,
                     var nama: String = "",
                     var tempatlahir: String = "",
                     var tanggallahir: LocalDate? = null,
                     var jeniskelamin: String? = null,
                     var alamat: String = "",
                     var rt: Int = -1,
                     var rw: Int = -1,
                     var desa: String = "",
                     var kecamatan: String = "",
                     var kabupaten: String = "",
                     var provinsi: String = "",
                     var agama: String = "",
                     var statusperkawinan: String = "",
                     var pekerjaan: String = "",
                     var kewarganegaraan: String = ""){
}
