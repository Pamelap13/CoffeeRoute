package com.example.coffeeroute

import com.google.firebase.firestore.GeoPoint
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ItemCoffee(
    val id: Int,
    val name: String,
    val address: String,
    val instagram: String,
    val image: String,
    @Serializable(with = GeoPointSerializer::class)
    val coordinate: GeoPoint
)


object GeoPointSerializer : KSerializer<GeoPoint> {
    override val descriptor = PrimitiveSerialDescriptor("GeoPoint", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: GeoPoint) {
        val serializedValue = "${value.latitude},${value.longitude}"
        encoder.encodeString(serializedValue)
    }

    override fun deserialize(decoder: Decoder): GeoPoint {
        val (latitude, longitude) = decoder.decodeString().split(",").map { it.toDouble() }
        return GeoPoint(latitude, longitude)
    }
}