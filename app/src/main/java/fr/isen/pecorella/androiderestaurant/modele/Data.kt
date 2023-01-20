package fr.isen.pecorella.androiderestaurant

import com.google.gson.annotations.SerializedName
import fr.isen.pecorella.androiderestaurant.modele.Items


data class Data (

  @SerializedName("name_fr" ) var nameFr : String?          = null,
  @SerializedName("name_en" ) var nameEn : String?          = null,
  @SerializedName("items"   ) var items  : ArrayList<Items> = arrayListOf()

)