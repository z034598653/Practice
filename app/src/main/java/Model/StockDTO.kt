package Model

import com.google.gson.annotations.SerializedName

class StockDTO {
    @SerializedName("state") var stat: String = ""
    @SerializedName("date") var date: String? = ""
    @SerializedName("title") var title: String? = ""
    @SerializedName("fields")var fields: List<String?> = arrayListOf()
    @SerializedName("data") var data: List<List<String?>> = arrayListOf(arrayListOf())
    //var data: String? = ""
    fun anc(): String {
        return stat + date + title
    }
}
