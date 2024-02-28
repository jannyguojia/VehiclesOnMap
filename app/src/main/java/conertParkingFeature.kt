import com.google.gson.JsonObject
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Geometry
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import org.json.JSONArray
import org.json.JSONObject

class conertParkingFeature {
    fun extractCoordinatesFromGeoJson(geoJsonString: String): List<Point> {
        val featureCollection = FeatureCollection.fromJson(geoJsonString)

        val coordinates = mutableListOf<Point>()

        featureCollection.features()?.forEach { feature ->
            val geometry = feature.geometry()
            if (geometry is Polygon) {
                // 外层坐标
                val outerCoordinates = geometry.outer() ?: return@forEach
                coordinates.addAll(outerCoordinates.coordinates())
            }
        }

        return coordinates
    }

}