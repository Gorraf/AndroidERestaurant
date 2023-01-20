package fr.isen.pecorella.androiderestaurant.modele

import com.google.gson.annotations.SerializedName
import fr.isen.pecorella.androiderestaurant.Ingredients
import fr.isen.pecorella.androiderestaurant.Prices


data class Items (

  @SerializedName("id"            ) var id          : String?                = null,
  @SerializedName("name_fr"       ) var nameFr      : String?                = null,
  @SerializedName("name_en"       ) var nameEn      : String?                = null,
  @SerializedName("id_category"   ) var idCategory  : String?                = null,
  @SerializedName("categ_name_fr" ) var categNameFr : String?                = null,
  @SerializedName("categ_name_en" ) var categNameEn : String?                = null,
  @SerializedName("images"        ) var images      : ArrayList<String>      = arrayListOf(),
  @SerializedName("ingredients"   ) var ingredients : ArrayList<Ingredients> = arrayListOf(),
  @SerializedName("prices"        ) var prices      : ArrayList<Prices>      = arrayListOf()

)