package ru.mirea.shurchkov.mireaproject;

import android.os.Bundle;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.mapview.MapView;

import androidx.appcompat.app.AppCompatActivity;

//import ru.mirea.shurchkov.mireaproject.databinding.ActivityOpenMapBinding;

public class OpenMapActivity extends AppCompatActivity{}

//    private ActivityOpenMapBinding binding;
//    private final String MAPKIT_API_KEY = "bcdc08a4-7576-4d94-a901-0e51a387228c";
//    private final Point ROUTE_START_LOCATION = new Point(55.584777, 37.903695);
//    private final Point ROUTE_END_LOCATION = new Point(55.769008, 37.644612);
//    private final Point SCREEN_CENTER = new Point(
//            (ROUTE_START_LOCATION.getLatitude() + ROUTE_END_LOCATION.getLatitude()) / 2,
//            (ROUTE_START_LOCATION.getLongitude() + ROUTE_END_LOCATION.getLongitude()) /
//                    2);
//    private MapView mapView;
//    private int[] colors = {0xFFFF0000, 0xFF00FF00, 0x00FFBBBB, 0xFF0000FF};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        MapKitFactory.setApiKey(MAPKIT_API_KEY);
//        MapKitFactory.initialize(this);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_open_map2);
//        mapView = findViewById(R.id.mapView);
//    }
//
//    @Override
//    protected void onStop() {
//        mapView.onStop();
//        MapKitFactory.getInstance().onStop();
//        super.onStop();
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        MapKitFactory.getInstance().onStart();
//        mapView.onStart();
//    }
//}
