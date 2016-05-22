package bit.simle1.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap mMap;
    LatLng dunedinLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        double lat = -45.8788;
        double lng = 170.5028;

        dunedinLatLng = new LatLng(lat, lng);

        mapFragment.getMapAsync(new MapCallBackClass());
    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(dunedinLatLng).title("Dunedin!!"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(dunedinLatLng));
        }
    }
}
