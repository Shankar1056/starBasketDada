package apextechies.starbasketseller.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.Imagedapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.InsertProductModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import apextechies.starbasketseller.takeandpickimagelib.DefaultCallback
import apextechies.starbasketseller.takeandpickimagelib.EasyImage
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.android.synthetic.main.common_toolbar.*
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddProductAcvtivity : AppCompatActivity() {
    private val PHOTOS_KEY = "easy_image_photos_list"
    private var photos = ArrayList<File>()
    private var retrofitDataProvider: RetrofitDataProvider? = null
    private val permision = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest
            .permission.CAMERA)
    private var imageAdapter: Imagedapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)
        initWidgit()
        Nammu.init(this)
        checkPermission()
        checkGalleryAppAvailability()

        if (savedInstanceState != null) {
            photos = savedInstanceState.getSerializable(PHOTOS_KEY) as ArrayList<File>
        }


    }


    private fun initWidgit() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        imageRV.layoutManager = GridLayoutManager(this, 3)
        imageRV.isNestedScrollingEnabled = false
        retrofitDataProvider = RetrofitDataProvider(this)

        imageAdapter = Imagedapter(this, path, object : OnItemClickListener {
            override fun onClick(pos: Int, text: String) {
                path.removeAt(pos)
                imageAdapter!!.notifyDataSetChanged()
            }
        })
        imageRV.adapter = imageAdapter

        if (intent.getStringExtra("operation").equals("update")) {
            supportActionBar!!.title = "Update Varient"
            submit.setText("Update")
            productName.isClickable = false
            productName.isFocusable = false
            productName.setText(intent.getStringExtra("name"))
            productUnit.setText(intent.getStringExtra("unit"))
            productQuantity.setText(intent.getStringExtra("productQuantity"))
            productActual_price.setText(intent.getStringExtra("actual_price"))
            productSelling_price.setText(intent.getStringExtra("selling_price"))
            productShortDescription.setText(intent.getStringExtra("short_description"))
            productFullDescription.setText(intent.getStringExtra("full_description"))
            productBrand.setText(intent.getStringExtra("product_brand"))
        } else if (intent.getStringExtra("operation").equals("insert")) {
            supportActionBar!!.title = "Add Varient"
            productName.isFocusable = false
            productName.isClickable = false
            productName.setText(intent.getStringExtra("name"))
        }




        submit.setOnClickListener {
            validateAndInsert()

        }

        toolbar.setNavigationOnClickListener {
            finish()
        }
        EasyImage.configuration(this)
                .setImagesFolderName()
                .setCopyTakenPhotosToPublicGalleryAppFolder()
                .setCopyPickedImagesToPublicGalleryAppFolder()
                .setAllowMultiplePickInGallery()

        imageView.setOnClickListener {
            EasyImage.openChooserWithGallery(this, "Pick source", 0)
        }

    }

    private fun checkPermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(this, permision, object : PermissionCallback {
                override fun permissionGranted() {
                    //Nothing, this sample saves to Public gallery so it needs permission
                }

                override fun permissionRefused() {

                }
            })
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(PHOTOS_KEY, photos)
    }

    private fun checkGalleryAppAvailability() {
        if (!EasyImage.canDeviceHandleGallery(this)) {
            //Device has no app that handles gallery intent
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
            override fun onImagePickerError(e: Exception, source: EasyImage.ImageSource, type: Int) {
                //Some error handling
                e.printStackTrace()
            }

            override fun onImagesPicked(imageFiles: List<File>, source: EasyImage.ImageSource, type: Int) {
                onPhotosReturned(imageFiles)
            }

            override fun onCanceled(source: EasyImage.ImageSource, type: Int) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source === EasyImage.ImageSource.CAMERA) {
                    val photoFile = EasyImage.lastlyTakenButCanceledPhoto(this@AddProductAcvtivity)
                    photoFile?.delete()
                }
            }

        })
    }

    var path = ArrayList<String>()
    private fun onPhotosReturned(returnedPhotos: List<File>) {
        photos.addAll(returnedPhotos)
        val uri = Uri.fromFile(returnedPhotos[0])
        path.add(uri.toString())
        imageAdapter!!.notifyDataSetChanged()
        /* Picasso.with(this)
                 .load(returnedPhotos.get(0))
                 .fit()
                 .centerCrop()
                 .into(imageView)*/
    }

    private fun validateAndInsert() {
        val c = Calendar.getInstance().getTime()
        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c)

        if (TextUtils.isEmpty(productName.text.toString().trim())) Utilz.showToast(this, "Enter your product name")
        else if (TextUtils.isEmpty(productUnit.text.toString().trim())) Utilz.showToast(this, "Enter varient")
        else if (TextUtils.isEmpty(productQuantity.text.toString().trim())) Utilz.showToast(this, "Enter Quantity")
        else if (TextUtils.isEmpty(productBrand.text.toString().trim())) Utilz.showToast(this, "Enter product brand price")
        else if (TextUtils.isEmpty(productActual_price.text.toString().trim())) Utilz.showToast(this, "Enter product actual price")
        else if (TextUtils.isEmpty(productSelling_price.text.toString().trim())) Utilz.showToast(this, "Enter product selling price")
        else if (TextUtils.isEmpty(productShortDescription.text.toString().trim())) Utilz.showToast(this, "Enter product short description")
        else if (TextUtils.isEmpty(productFullDescription.text.toString().trim())) Utilz.showToast(this, "Describe about your product")
        else {
            Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
            if (intent.getStringExtra("operation").equals("newinsert")) {

                retrofitDataProvider!!.insertProduct(intent.getStringExtra("sub_cat_id"), intent.getStringExtra("sub_sub_cat_id"),
                        productName.text.toString(), productUnit.text.toString(), productQuantity.text.toString(), productBrand.text.toString(), productActual_price.text.toString(), productSelling_price.text.toString(), "10", productShortDescription.text.toString(),
                        productFullDescription.text.toString(), ClsGeneral.getStrPreferences(this, AppConstants.USERID), formattedDate, object : DownlodableCallback<InsertProductModel> {
                    override fun onSuccess(result: InsertProductModel) {
                        if (result.status!!.contains(AppConstants.TRUE)) {
                            Utilz.closeDialog()
                            startActivity(Intent(this@AddProductAcvtivity, ProductListActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@AddProductAcvtivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(error: String) {
                        Utilz.closeDialog()
                        Toast.makeText(this@AddProductAcvtivity, "" + error, Toast.LENGTH_SHORT).show()
                    }

                    override fun onUnauthorized(errorNumber: Int) {
                        Utilz.closeDialog()
                        Toast.makeText(this@AddProductAcvtivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                retrofitDataProvider!!.insertUpdate(intent.getStringExtra("id"), intent.getStringExtra("prod_id"),
                        productUnit.text.toString(), productQuantity.text.toString(), productBrand.text.toString(), productActual_price.text.toString(), productSelling_price.text.toString(), "10", productShortDescription.text.toString(),
                        productFullDescription.text.toString(), formattedDate, intent.getStringExtra("operation"), object : DownlodableCallback<InsertProductModel> {
                    override fun onSuccess(result: InsertProductModel) {
                        if (result.status!!.contains(AppConstants.TRUE)) {
                            Utilz.closeDialog()
                            startActivity(Intent(this@AddProductAcvtivity, ProductListActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@AddProductAcvtivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(error: String) {
                        Utilz.closeDialog()
                        Toast.makeText(this@AddProductAcvtivity, "" + error, Toast.LENGTH_SHORT).show()
                    }

                    override fun onUnauthorized(errorNumber: Int) {
                        Utilz.closeDialog()
                        Toast.makeText(this@AddProductAcvtivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }


    override fun onDestroy() {
        // Clear any configuration that was done!
        EasyImage.clearConfiguration(this)
        super.onDestroy()
    }
}