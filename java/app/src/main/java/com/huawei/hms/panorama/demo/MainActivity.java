/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.hms.panorama.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huawei.hms.panorama.Panorama;
import com.huawei.hms.panorama.PanoramaInterface;
import com.huawei.hms.support.api.client.ResultCallback;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * function description
 *
 * @author huawei
 * @since 2020-04-20
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "MainActivity";

    private Button mButtonDisplayInHms;
    private Button mButtonDisplayInHmsWithPerm;
    private Button mButtonDisplayInHmsRing;
    private Button mButtonDisplayInHmsPickPhoto;
    private Button mButtonDisplayInAppSpherical;
    private Button mButtonDisplayInAppRing;
    private Button mButtonDisplayInAppPolar;
    private Button mButtonDisplayInAppVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonDisplayInHms = findViewById(R.id.buttonInHms);
        mButtonDisplayInHms.setOnClickListener(this);
        mButtonDisplayInHmsWithPerm = findViewById(R.id.buttonInHmsWithPerm);
        mButtonDisplayInHmsWithPerm.setOnClickListener(this);
        mButtonDisplayInHmsRing = findViewById(R.id.buttonInHmsRing);
        mButtonDisplayInHmsRing.setOnClickListener(this);
        mButtonDisplayInHmsPickPhoto = findViewById(R.id.buttonInHmsPickPhoto);
        mButtonDisplayInHmsPickPhoto.setOnClickListener(this);
        mButtonDisplayInAppSpherical = findViewById(R.id.buttonInAppSpherical);
        mButtonDisplayInAppSpherical.setOnClickListener(this);
        mButtonDisplayInAppRing = findViewById(R.id.buttonInAppRing);
        mButtonDisplayInAppRing.setOnClickListener(this);
        mButtonDisplayInAppPolar = findViewById(R.id.buttonInAppPolar);
        mButtonDisplayInAppPolar.setOnClickListener(this);
        mButtonDisplayInAppVideo = findViewById(R.id.buttonInAppVideo);
        mButtonDisplayInAppVideo.setOnClickListener(this);

        checkPermission();
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.buttonInHms:
            case R.id.buttonInHmsWithPerm:
            case R.id.buttonInHmsRing:
            case R.id.buttonInHmsPickPhoto:
                displayInHms(view.getId());
                break;
            case R.id.buttonInAppSpherical:
            case R.id.buttonInAppRing:
            case R.id.buttonInAppPolar:
            case R.id.buttonInAppVideo:
                displayInApp(view.getId());
                break;
            default:
                break;
        }
    }

    private class ResultCallbackImpl implements ResultCallback<PanoramaInterface.ImageInfoResult> {
        @Override
        public void onResult(PanoramaInterface.ImageInfoResult panoramaResult) {
            if (panoramaResult == null) {
                logAndToast("panoramaResult is null");
                return;
            }

            if (panoramaResult.getStatus().isSuccess()) {
                Intent intent = panoramaResult.getImageDisplayIntent();
                if (intent != null) {
                    startActivity(intent);
                } else {
                    logAndToast("unknown error, view intent is null");
                }
            } else {
                logAndToast("error status : " + panoramaResult.getStatus());
            }
        }
    }

    private void pickPhotoAndDisplay() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 10001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(LOG_TAG, "onActivityResult: received!");
        if (requestCode != 10001 || resultCode != Activity.RESULT_OK) {
            logAndToast("onActivityResult requestCode or resultCode invalid");
            return;
        }

        if (data == null) {
            logAndToast("onActivityResult data is null");
            return;
        }

        Panorama.getInstance().loadImageInfoWithPermission(
                this, data.getData(), PanoramaInterface.IMAGE_TYPE_SPHERICAL)
                .setResultCallback(new ResultCallbackImpl());
    }

    private void displayInHms(int id) {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pano);
        switch (id) {
            case R.id.buttonInHms:
                Panorama.getInstance().loadImageInfo(this, uri).setResultCallback(new ResultCallbackImpl());
                break;
            case R.id.buttonInHmsWithPerm:
                Panorama.getInstance().loadImageInfoWithPermission(this, uri)
                        .setResultCallback(new ResultCallbackImpl());
                break;
            case R.id.buttonInHmsRing:
                Panorama.getInstance()
                        .loadImageInfo(this, uri, PanoramaInterface.IMAGE_TYPE_RING)
                        .setResultCallback(new ResultCallbackImpl());
                break;
            case R.id.buttonInHmsPickPhoto:
                pickPhotoAndDisplay();
                break;
            default:
                logAndToast("displayInHms invalid id " + id);
                break;
        }
    }

    private void displayInApp(int id) {
        Intent intent = new Intent(MainActivity.this, LocalDisplayActivity.class);
        intent.putExtra("ViewId", id);
        startActivity(intent);
    }

    private void logAndToast(String message) {
        Log.e(LOG_TAG, message);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Log.i(LOG_TAG, "permission ok");
        }
    }
}
