package com.zoro.studdis.ui.main.fragments.qreader;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.android.material.snackbar.Snackbar;
import com.zoro.studdis.R;
import com.zoro.studdis.views.PointsOverlayView;

public class QReaderFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {

    private QReaderViewModel mViewModel;

    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

    public static QReaderFragment newInstance() {
        return new QReaderFragment();
    }

    private TextView qrText;
    private QRCodeReaderView qrCodeReaderView;
    private View qrReaderFragmentMainLayout;
    private PointsOverlayView pointsOverlayView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.qreader_fragment, container, false);

        qrReaderFragmentMainLayout = rootView.findViewById(R.id.qrReaderFragmentMainLayout);

        qrText = rootView.findViewById(R.id.qrText);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QReaderViewModel.class);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            initQRCodeReaderView();
        } else {
            requestCameraPermission();
        }

    }

    private void initQRCodeReaderView() {
        qrCodeReaderView = getView().findViewById(R.id.qrCodeReader);

        pointsOverlayView = getView().findViewById(R.id.pointsOverlayView);

        qrCodeReaderView.setOnQRCodeReadListener(this);
        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);
        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);
        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);
        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        qrCodeReaderView.startCamera();
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            Snackbar.make(qrReaderFragmentMainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.CAMERA
                    }, MY_PERMISSION_REQUEST_CAMERA);
                }
            }).show();
        } else {
            Snackbar.make(qrReaderFragmentMainLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(qrReaderFragmentMainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
            initQRCodeReaderView();
        } else {
            Snackbar.make(qrReaderFragmentMainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrText.setText(text);
        pointsOverlayView.setPoints(points);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( qrCodeReaderView != null )
            qrCodeReaderView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        if ( qrCodeReaderView != null )
            qrCodeReaderView.stopCamera();
    }
}