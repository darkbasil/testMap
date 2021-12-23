package com.example.testmap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testmap.databinding.FragmentMapBinding
import com.example.testmap.utils.Root
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private var myOverlay: ItemizedIconOverlay<OverlayItem>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapView: MapView = binding.map
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapView.setMultiTouchControls(true)

        val mapController = mapView.controller
        mapController.setZoom(13.0)

        val startPoint = GeoPoint(55.761148,37.623304)
        mapController.setCenter(startPoint)

        val sharedViewModel = this.activityViewModels<SharedViewModel>().value

        sharedViewModel.root.value?.let { updateMarkers(it, mapView) }

        binding.buttonMap.setOnClickListener {
            findNavController().navigate(R.id.action_MapFragment_to_FilterFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateMarkers(root: Root, mapView: MapView) {
        val items = ArrayList<OverlayItem>()
        val activeServices = root.services.filter { it.checked }.map { it.name }

        for(pin in root.pins) {
            if (pin.service in activeServices) {
                val item = OverlayItem(pin.id.toString(), pin.service, GeoPoint(pin.coordinates.lat, pin.coordinates.lng))
                val marker = when (pin.service) {
                    "a" -> this.resources.getDrawable(R.drawable.circle1, null)
                    "b" -> this.resources.getDrawable(R.drawable.circle3, null)
                    "c" -> this.resources.getDrawable(R.drawable.circle2, null)
                    else -> this.resources.getDrawable(R.drawable.marker_default, null)
                }
                item.setMarker(marker)
                items.add(item)
            }
        }

        myOverlay = ItemizedIconOverlay(items,
            object : OnItemGestureListener<OverlayItem> {
                override fun onItemSingleTapUp(
                    index: Int,
                    item: OverlayItem
                ): Boolean {
                    Toast.makeText(
                        context,
                        item.title, Toast.LENGTH_LONG
                    ).show()
                    return true // We 'handled' this event.
                }

                override fun onItemLongPress(
                    index: Int,
                    item: OverlayItem
                ): Boolean {
                    Toast.makeText(
                        context,
                        item.snippet, Toast.LENGTH_LONG
                    ).show()
                    return false
                }
            }, context
        )

        mapView.overlays.add(myOverlay)

        mapView.invalidate()
    }

}