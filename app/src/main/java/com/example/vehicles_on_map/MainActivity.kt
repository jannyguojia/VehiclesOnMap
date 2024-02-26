package com.example.vehicles_on_map


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.image.image
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style

//create a free Mapbox account to access the required keys.
// class MainActivity : ComponentActivity() {
//    private lateinit var mapView: MapView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Create a map programmatically and set the initial camera
//        mapView = MapView(this)
//        mapView.mapboxMap.setCamera(
//            CameraOptions.Builder()
//                .center(Point.fromLngLat(174.781504, -41.273645))
//                .pitch(0.0)
//                .zoom(2.0)
//                .bearing(0.0)
//                .build()
//        )
//        // Add the map view to the activity (you can also add it to other views as a child)
//        setContentView(mapView)
//
//    }
//}


class MainActivity : ComponentActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapView = MapView(this)
        setContentView(mapView)
        mapView.mapboxMap.setCamera(
            CameraOptions.Builder().center(
                Point.fromLngLat(
                    LATITUDE,
                    LONGITUDE
                )
            ).zoom(ZOOM).build()
        )


        mapView.mapboxMap.loadStyle(
                    style(style = Style.STANDARD) {
                        +image(
                            "vehicle",
                            BitmapFactory.decodeResource(resources, R.drawable.vehicle)
                        )
                        +geoJsonSource(GEOJSON_SOURCE_ID) {
                            data("""{
                        "type": "FeatureCollection",
                        "features": [
                            {
                                "type": "Feature",
                                "geometry": {
                                    "type": "Point",
                                    "coordinates": [174.781504, -41.273645]
                                },
                                "properties": {
                                    "iconName": "vehicle" 
                                }
                            },
                            // Add more features as needed
                        ]
                    }""")
                        }
                        +symbolLayer("symbolLayer", GEOJSON_SOURCE_ID) {
                            iconImage(get("iconName"))
                            iconAllowOverlap(true)
                            iconIgnorePlacement(true)
                            iconOptional(true)
                        }
                    }
                    )

    }

    companion object {
        private const val GEOJSON_SOURCE_ID = "vehicle"
        private const val LATITUDE = 174.781504
        private const val LONGITUDE = -41.273645
        private const val ZOOM = 14.0
    }

}