package com.example.vehicles_on_map


import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Geometry
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.geojson.Polygon
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.image.image
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.layers.generated.lineLayer
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.light.generated.ambientLight
import com.mapbox.maps.extension.style.light.generated.directionalLight
import com.mapbox.maps.extension.style.light.setLight
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style
import conertParkingFeature
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.animation.CameraAnimatorOptions.Companion.cameraAnimatorOptions
import com.mapbox.maps.plugin.animation.camera

import org.json.JSONArray
import org.json.JSONObject


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


        //Traverse to obtain all vehicle coordinates
        fun convertToFeatureCollection(jsonString: String): FeatureCollection {
            val json = JSONObject(jsonString)
            val featureArray = json.getJSONObject("data").getJSONArray("features")

            val features = mutableListOf<Feature>()
            for (i in 0 until featureArray.length()) {
                val featureObject = featureArray.getJSONObject(i)
                val geometry = featureObject.getJSONObject("geometry")
                val coordinatesArray = geometry.getJSONArray("coordinates")
                val lng = coordinatesArray.getDouble(0)
                val lat = coordinatesArray.getDouble(1)

                val point = Point.fromLngLat(lng, lat)
                val feature = Feature.fromGeometry(point)
                features.add(feature)
            }

            return FeatureCollection.fromFeatures(features)
        }


        val featureCollection = convertToFeatureCollection("""
        {"type":"geojson","data":{"type":"FeatureCollection","features":[{"type":"Feature","geometry":{"type":"Point","coordinates":["174.773914","-41.296646"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.792448","-41.291667"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783283","-41.310685"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.769165","-41.28585"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.779776","-41.280758"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.770304","-41.300512"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.779302","-41.296931"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776294","-41.294621"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.792192","-41.291027"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.760768","-41.293405"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.788339","-41.294237"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783168","-41.310714"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.786035","-41.291072"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.7824","-41.292934"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.779917","-41.295747"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.769485","-41.301693"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.774387","-41.292554"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.774042","-41.28849"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.812954","-41.327914"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.770227","-41.293344"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.770227","-41.294947"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.771008","-41.281654"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783155","-41.310656"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.782195","-41.293328"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775309","-41.293114"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777933","-41.27351"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.770035","-41.299066"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.78327","-41.310624"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.820032","-41.316547"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777869","-41.274406"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776256","-41.294522"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.771174","-41.304134"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.764646","-41.28625"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.774912","-41.33408"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.78217","-41.293517"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77088","-41.300454"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777805","-41.286982"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77431","-41.292339"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.789171","-41.293114"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77239","-41.288048"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.774374","-41.292592"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.781901","-41.310534"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777971","-41.273059"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.812979","-41.327891"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777741","-41.333446"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.779251","-41.281299"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783206","-41.31072"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77481","-41.291107"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.761446","-41.288288"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.812941","-41.327923"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.761075","-41.287578"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.813517","-41.319043"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775258","-41.331606"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778163","-41.275514"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.787213","-41.31489"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777805","-41.276557"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.812672","-41.33039"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777882","-41.273446"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77673","-41.28488"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783232","-41.310662"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777344","-41.29023"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.779149","-41.274314"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.760525","-41.293485"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.760397","-41.286672"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783322","-41.310669"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.773184","-41.276896"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77431","-41.296"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.772646","-41.280003"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776282","-41.29447"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.820736","-41.312995"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776102","-41.289296"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.77495","-41.333763"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.810125","-41.325312"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.78327","-41.310662"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778112","-41.27703"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.810163","-41.325181"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.761613","-41.288493"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777331","-41.28353"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.785997","-41.291142"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777805","-41.273114"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775962","-41.289002"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778586","-41.283978"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775181","-41.279491"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.789466","-41.293069"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778906","-41.305174"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783194","-41.310602"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778061","-41.303888"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.772173","-41.330813"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778509","-41.283741"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777894","-41.273834"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.763917","-41.28912"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.798963","-41.288486"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777766","-41.286854"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776102","-41.294925"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.761331","-41.288083"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775514","-41.280362"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.765696","-41.287606"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.783859","-41.295859"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.769587","-41.341245"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.812851","-41.328464"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.775181","-41.280902"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.786317","-41.312582"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.786304","-41.312336"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.776205","-41.295571"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.777818","-41.277837"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.782886","-41.292432"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.774016","-41.2812"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.767424","-41.296634"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.778829","-41.277011"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.765453","-41.290294"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}},{"type":"Feature","geometry":{"type":"Point","coordinates":["174.765926","-41.291795"]},"properties":{"iconUrl":"https://assets.mevo.co.nz/vehicles/pin-vehicle-available.png"}}]}}
    """.trimIndent())


//       val converter = conertParkingFeature()
//        val geoJsonString = "{\"type\":\"geojson\",\"data\":{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[[174.76829,-41.294524],[174.76907,-41.293253],[174.766324,-41.292379],[174.764726,-41.292354],[174.761642,-41.291778],[174.76197,-41.290524],[174.76183,-41.2895],[174.760956,-41.288335],[174.75966,-41.286729],[174.761337,-41.284558],[174.766685,-41.28532],[174.768941,-41.2845],[174.769576,-41.282921],[174.770654,-41.281395],[174.77053,-41.280353],[174.772858,-41.279235],[174.773458,-41.280064],[174.773305,-41.27853],[174.773277,-41.276968],[174.771621,-41.276933],[174.769912,-41.275783],[174.779339,-41.267987],[174.779949,-41.267626],[174.781086,-41.266679],[174.781918,-41.267167],[174.781462,-41.268279],[174.781342,-41.269374],[174.782562,-41.271749],[174.783834,-41.273808],[174.783905,-41.274085],[174.78389,-41.274491],[174.78371,-41.275061],[174.783441,-41.275455],[174.782964,-41.27574],[174.781534,-41.277143],[174.780094,-41.278599],[174.782147,-41.279795],[174.781899,-41.280026],[174.781156,-41.280479],[174.78127,-41.281051],[174.780682,-41.281127],[174.780502,-41.281691],[174.779714,-41.281793],[174.779382,-41.282143],[174.779523,-41.283021],[174.77915,-41.283833],[174.779608,-41.285949],[174.779256,-41.286166],[174.779614,-41.287778],[174.780201,-41.288227],[174.780424,-41.289583],[174.783409,-41.289315],[174.783647,-41.290526],[174.784727,-41.290426],[174.786448,-41.290636],[174.786177,-41.2913],[174.78648,-41.291437],[174.788586,-41.291284],[174.78896,-41.290398],[174.790488,-41.289851],[174.793567,-41.290677],[174.800391,-41.287227],[174.800922,-41.287844],[174.798987,-41.289063],[174.797771,-41.292067],[174.796407,-41.29306],[174.794718,-41.293366],[174.794508,-41.292697],[174.794058,-41.292395],[174.791665,-41.292883],[174.789336,-41.294286],[174.791262,-41.294838],[174.786825,-41.302693],[174.781894,-41.301035],[174.779504,-41.305578],[174.780187,-41.306488],[174.780273,-41.307439],[174.785742,-41.306702],[174.787753,-41.313157],[174.789025,-41.313633],[174.789926,-41.314302],[174.78976,-41.315057],[174.788435,-41.315571],[174.786616,-41.31652],[174.787284,-41.318904],[174.785729,-41.31915],[174.78515,-41.318861],[174.781277,-41.31952],[174.781374,-41.319854],[174.781198,-41.324484],[174.77122,-41.324307],[174.771413,-41.318731],[174.77373,-41.318731],[174.773865,-41.315351],[174.774283,-41.313689],[174.773536,-41.312377],[174.772228,-41.310004],[174.772488,-41.307374],[174.77277,-41.304921],[174.770799,-41.304525],[174.769085,-41.302935],[174.768033,-41.300016],[174.768092,-41.299116],[174.768661,-41.297731],[174.767008,-41.29723],[174.765471,-41.298335],[174.7646,-41.298054],[174.765522,-41.296369],[174.760319,-41.293895],[174.760812,-41.292557],[174.76829,-41.294524]],[[174.773769,-41.339912],[174.774131,-41.340674],[174.77434,-41.342636],[174.774197,-41.343021],[174.768583,-41.34178],[174.771162,-41.335308],[174.769815,-41.33367],[174.770856,-41.33197],[174.772294,-41.328828],[174.775941,-41.32903],[174.77562,-41.332534],[174.778173,-41.332671],[174.778152,-41.333582],[174.776987,-41.33456],[174.775504,-41.334494],[174.77532,-41.336068],[174.773769,-41.339912]],[[174.813339,-41.320104],[174.812312,-41.315929],[174.81738,-41.3099],[174.818802,-41.309592],[174.819716,-41.306301],[174.821501,-41.304263],[174.825567,-41.305287],[174.826372,-41.305295],[174.826382,-41.307028],[174.823872,-41.312798],[174.821597,-41.314087],[174.819382,-41.31783],[174.813339,-41.320104]],[[174.812118,-41.330639],[174.812284,-41.327757],[174.813169,-41.327775],[174.812987,-41.330677],[174.812118,-41.330639]]]},\"properties\":{\"area\":\"9381487.0\",\"stroke\":\"#F7590D\",\"stroke-opacity\":1.0,\"stroke-width\":2,\"fill\":\"#FFFFFF\",\"fill-opacity\":0.5}}}" // 你的 GeoJSON 字符串
//        val polygonCoordinates = converter.extractCoordinatesFromGeoJson(geoJsonString)
//        println("Polygon Coordinates: $polygonCoordinates")
//        val outerLineString: LineString = LineString.fromLngLats(polygonCoordinates)

        val outerLineString: LineString = LineString.fromLngLats(POLYGON_COORDINATES)
        val outerLineString2: LineString = LineString.fromLngLats(POLYGON_COORDINATES2)
        val outerLineString0: LineString = LineString.fromLngLats(POLYGON_COORDINATES0)
        val outerLineString3: LineString = LineString.fromLngLats(POLYGON_COORDINATES3)
      // println("outerLineString3: $outerLineString3")

        //display the vehicle positions on the map.
        mapView.mapboxMap.loadStyle(
            style(style = Style.STANDARD) {
                //Animate the map camera. Individual camera properties such as zoom, bearing, and center coordinate can be animated independently.
                mapView.camera.apply {
                    val bearing = createBearingAnimator(cameraAnimatorOptions(-45.0)) {
                        duration = 4000
                        interpolator = AccelerateDecelerateInterpolator()
                    }
                    val zoom = createZoomAnimator(
                        cameraAnimatorOptions(14.0) {
                            startValue(3.0)
                        }
                    ) {
                        duration = 4000
                        interpolator = AccelerateDecelerateInterpolator()
                    }
                    val pitch = createPitchAnimator(
                        cameraAnimatorOptions(55.0) {
                            startValue(0.0)
                        }
                    ) {
                        duration = 4000
                        interpolator = AccelerateDecelerateInterpolator()
                    }
                    playAnimatorsSequentially(zoom, pitch, bearing)
                }

                //Read geojson data and display the vehicle location icon in the layer
                +geoJsonSource(GEOJSON_SOURCE_ID) {
                    data(featureCollection.toJson())
                }

                +image(VEHICLES, BitmapFactory.decodeResource(resources, R.drawable.vehicle))
                +symbolLayer("symbolLayer", GEOJSON_SOURCE_ID) {
                    iconImage(VEHICLES)
                    iconSize(0.4)
                }
                //  +circleLayer("circleLayer", GEOJSON_SOURCE_ID) {
  //                  circleRadius(10.0)
   //                 circleColor("#FF00FF")
    //                circleOpacity(0.7)
    //            }
              +geoJsonSource(PARKINGSOURCE_ID) {
                      feature(Feature.fromGeometry(Polygon.fromOuterInner(outerLineString0,outerLineString,outerLineString3,outerLineString2)))

              }
                +fillLayer(LAYER_ID, PARKINGSOURCE_ID) {
                    fillColor(Color.parseColor("#FFFFFF")).fillOpacity(0.5)
                }
                +lineLayer(TOP_LAYER_ID, PARKINGSOURCE_ID) {
                    lineColor("#F7590D")
                    lineWidth(2.0)
                }


            }
        )
    }

    companion object {
        private const val GEOJSON_SOURCE_ID = "vehicle"
        private const val LATITUDE = 174.781504
        private const val LONGITUDE = -41.273645
        private const val ZOOM = 14.0
        private const val VEHICLES = "vehicles"
        //private val PARKINGSOURCE_URL = "https://api.mevo.co.nz/public/parking/wellington"
        val POLYGON_COORDINATES = listOf(
            Point.fromLngLat(174.76829, -41.294524),
            Point.fromLngLat(174.76907, -41.293253),
            Point.fromLngLat(174.766324, -41.292379),
            Point.fromLngLat(174.764726, -41.292354),
            Point.fromLngLat(174.761642, -41.291778),
            Point.fromLngLat(174.76197, -41.290524),
            Point.fromLngLat(174.76183, -41.2895),
            Point.fromLngLat(174.760956, -41.288335),
            Point.fromLngLat(174.75966, -41.286729),
            Point.fromLngLat(174.761337, -41.284558),
            Point.fromLngLat(174.766685, -41.28532),
            Point.fromLngLat(174.768941, -41.2845),
            Point.fromLngLat(174.769576, -41.282921),
            Point.fromLngLat(174.770654, -41.281395),
            Point.fromLngLat(174.77053, -41.280353),
            Point.fromLngLat(174.772858, -41.279235),
            Point.fromLngLat(174.773458, -41.280064),
            Point.fromLngLat(174.773305, -41.27853),
            Point.fromLngLat(174.773277, -41.276968),
            Point.fromLngLat(174.771621, -41.276933),
            Point.fromLngLat(174.769912, -41.275783),
            Point.fromLngLat(174.779339, -41.267987),
            Point.fromLngLat(174.779949, -41.267626),
            Point.fromLngLat(174.781086, -41.266679),
            Point.fromLngLat(174.781918, -41.267167),
            Point.fromLngLat(174.781462, -41.268279),
            Point.fromLngLat(174.781342, -41.269374),
            Point.fromLngLat(174.782562, -41.271749),
            Point.fromLngLat(174.783834, -41.273808),
            Point.fromLngLat(174.783905, -41.274085),
            Point.fromLngLat(174.78389, -41.274491),
            Point.fromLngLat(174.78371, -41.275061),
            Point.fromLngLat(174.783441, -41.275455),
            Point.fromLngLat(174.782964, -41.27574),
            Point.fromLngLat(174.781534, -41.277143),
            Point.fromLngLat(174.780094, -41.278599),
            Point.fromLngLat(174.782147, -41.279795),
            Point.fromLngLat(174.781899, -41.280026),
            Point.fromLngLat(174.781156, -41.280479),
            Point.fromLngLat(174.78127, -41.281051),
            Point.fromLngLat(174.780682, -41.281127),
            Point.fromLngLat(174.780502, -41.281691),
            Point.fromLngLat(174.779714, -41.281793),
            Point.fromLngLat(174.779382, -41.282143),
            Point.fromLngLat(174.779523, -41.283021),
            Point.fromLngLat(174.77915, -41.283833),
            Point.fromLngLat(174.779608, -41.285949),
            Point.fromLngLat(174.779256, -41.286166),
            Point.fromLngLat(174.779614, -41.287778),
            Point.fromLngLat(174.780201, -41.288227),
            Point.fromLngLat(174.780424, -41.289583),
            Point.fromLngLat(174.783409, -41.289315),
            Point.fromLngLat(174.783647, -41.290526),
            Point.fromLngLat(174.784727, -41.290426),
            Point.fromLngLat(174.786448, -41.290636),
            Point.fromLngLat(174.786177, -41.2913),
            Point.fromLngLat(174.78648, -41.291437),
            Point.fromLngLat(174.788586, -41.291284),
            Point.fromLngLat(174.78896, -41.290398),
            Point.fromLngLat(174.790488, -41.289851),
            Point.fromLngLat(174.793567, -41.290677),
            Point.fromLngLat(174.800391, -41.287227),
            Point.fromLngLat(174.800922, -41.287844),
            Point.fromLngLat(174.798987, -41.289063),
            Point.fromLngLat(174.797771, -41.292067),
            Point.fromLngLat(174.796407, -41.29306),
            Point.fromLngLat(174.794718, -41.293366),
            Point.fromLngLat(174.794508, -41.292697),
            Point.fromLngLat(174.794058, -41.292395),
            Point.fromLngLat(174.791665, -41.292883),
            Point.fromLngLat(174.789336, -41.294286),
            Point.fromLngLat(174.791262, -41.294838),
            Point.fromLngLat(174.786825, -41.302693),
            Point.fromLngLat(174.781894, -41.301035),
            Point.fromLngLat(174.779504, -41.305578),
            Point.fromLngLat(174.780187, -41.306488),
            Point.fromLngLat(174.780273, -41.307439),
            Point.fromLngLat(174.785742, -41.306702),
            Point.fromLngLat(174.787753, -41.313157),
            Point.fromLngLat(174.789025, -41.313633),
            Point.fromLngLat(174.789926, -41.314302),
            Point.fromLngLat(174.78976, -41.315057),
            Point.fromLngLat(174.788435, -41.315571),
            Point.fromLngLat(174.786616, -41.31652),
            Point.fromLngLat(174.787284, -41.318904),
            Point.fromLngLat(174.785729, -41.31915),
            Point.fromLngLat(174.78515, -41.318861),
            Point.fromLngLat(174.781277, -41.31952),
            Point.fromLngLat(174.781374, -41.319854),
            Point.fromLngLat(174.781198, -41.324484),
            Point.fromLngLat(174.77122, -41.324307),
            Point.fromLngLat(174.771413, -41.318731),
            Point.fromLngLat(174.77373, -41.318731),
            Point.fromLngLat(174.773865, -41.315351),
            Point.fromLngLat(174.774283, -41.313689),
            Point.fromLngLat(174.773536, -41.312377),
            Point.fromLngLat(174.772228, -41.310004),
            Point.fromLngLat(174.772488, -41.307374),
            Point.fromLngLat(174.77277, -41.304921),
            Point.fromLngLat(174.770799, -41.304525),
            Point.fromLngLat(174.769085, -41.302935),
            Point.fromLngLat(174.768033, -41.300016),
            Point.fromLngLat(174.768092, -41.299116),
            Point.fromLngLat(174.768661, -41.297731),
            Point.fromLngLat(174.767008, -41.29723),
            Point.fromLngLat(174.765471, -41.298335),
            Point.fromLngLat(174.7646, -41.298054),
            Point.fromLngLat(174.765522, -41.296369),
            Point.fromLngLat(174.760319, -41.293895),
            Point.fromLngLat(174.760812, -41.292557),
            Point.fromLngLat(174.76829, -41.294524)
        )
        val POLYGON_COORDINATES2 = listOf(
            Point.fromLngLat(174.773769, -41.339912),
            Point.fromLngLat(174.774131, -41.340674),
            Point.fromLngLat( 174.77434, -41.342636),
            Point.fromLngLat(  174.774197, -41.343021),
            Point.fromLngLat(174.768583, -41.34178),
            Point.fromLngLat(174.771162, -41.335308),
            Point.fromLngLat( 174.769815, -41.33367),
            Point.fromLngLat( 174.770856, -41.33197),
            Point.fromLngLat(174.772294, -41.328828),
            Point.fromLngLat( 174.775941, -41.32903),
            Point.fromLngLat(174.77562, -41.332534),
            Point.fromLngLat(174.778173, -41.332671),
            Point.fromLngLat(174.778152, -41.333582),
            Point.fromLngLat(174.776987, -41.33456),
            Point.fromLngLat(174.775504, -41.334494),
            Point.fromLngLat(174.77532, -41.336068),
            Point.fromLngLat(174.773769, -41.339912)
        )
        val POLYGON_COORDINATES0 = listOf(
            Point.fromLngLat(  174.813339, -41.320104),
            Point.fromLngLat( 174.812312, -41.315929),
            Point.fromLngLat(174.81738, -41.3099),
            Point.fromLngLat(174.818802, -41.309592),
            Point.fromLngLat( 174.819716, -41.306301),
            Point.fromLngLat( 174.821501, -41.304263),
            Point.fromLngLat( 174.825567, -41.305287),
            Point.fromLngLat( 174.826372, -41.305295),
            Point.fromLngLat(174.826382, -41.307028),
            Point.fromLngLat( 174.823872, -41.312798),
            Point.fromLngLat( 174.821597, -41.314087),
            Point.fromLngLat(174.819382, -41.31783),
            Point.fromLngLat(174.813339, -41.320104)
        )
        val POLYGON_COORDINATES3 = listOf(
            Point.fromLngLat(174.812118, -41.330639),
            Point.fromLngLat( 174.812284, -41.327757),
            Point.fromLngLat(  174.813169, -41.327775),
            Point.fromLngLat( 174.812987, -41.330677),
            Point.fromLngLat( 174.812118, -41.330639)
        )
        private  val PARKINGSOURCE_ID = "PARKINGGeoJsonSource"
        private const val LAYER_ID  ="layerid"
        private const val TOP_LAYER_ID  ="toplayerid"
        private const val AMBIENT_LIGHT_ID = "ambient_id"
        private const val DIRECTIONAL_LIGHT_ID = "directional_id"

    }
}