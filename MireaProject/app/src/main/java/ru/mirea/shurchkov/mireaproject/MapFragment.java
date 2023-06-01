package ru.mirea.shurchkov.mireaproject;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import ru.mirea.shurchkov.mireaproject.databinding.FragmentMapBinding;

public class MapFragment extends AppCompatActivity{

    private FragmentMapBinding binding;
    private final String MAPKIT_API_KEY = "bcdc08a4-7576-4d94-a901-0e51a387228c";
    private final Point ROUTE_START_LOCATION = new Point(55.584777, 37.903695);
    private final Point ROUTE_END_LOCATION = new Point(55.769008, 37.644612);
    private final Point SCREEN_CENTER = new Point(
            (ROUTE_START_LOCATION.getLatitude() + ROUTE_END_LOCATION.getLatitude()) / 2,
            (ROUTE_START_LOCATION.getLongitude() + ROUTE_END_LOCATION.getLongitude()) /
                    2);
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        mapView = findViewById(R.id.mapView);
        Map map = mapView.getMap();


        PlacemarkMapObject redSquareMarker = map.getMapObjects().addPlacemark(new Point(55.753930, 37.620795));
        redSquareMarker.setIcon(ImageProvider.fromResource(this,  org.osmdroid.library.R.drawable.osm_ic_follow_me_on));
        redSquareMarker.setText("Красная площадь");

        PlacemarkMapObject vorobevyGoryMarker = map.getMapObjects().addPlacemark(new Point(55.710906,  37.553295));
        vorobevyGoryMarker.setIcon(ImageProvider.fromResource(this,  org.osmdroid.library.R.drawable.osm_ic_follow_me_on));
        vorobevyGoryMarker.setText("Воробьевы горы");

    }


    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}

