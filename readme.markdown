 **

### 欢迎使用StarDialog

添加依赖：

```
//根目录
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
//依赖
dependencies {
	        implementation 'com.github.shangmengmeng:CommonDialog:1.0.2'
	}
```


** 
### Dialog的基本使用方法：


```
            R.id.btn_dialog_normal -> {
                StarDialog.Builder()
                    .setContent("请注意这张是假币")
                    .setOnStarDialogClickListener(object : StarDialogClickListener {
                        override fun onConfirm() =
                            Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                        override fun onCancel() {
                            Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .show(supportFragmentManager)
            }
```

StarDialog的自定义使用方法


```
  //自定义
            R.id.btn_dialog_custom -> {
                val option = DialogSettingOption()
                option.titleColor = Color.parseColor("#333333")
                option.contentColor = Color.parseColor("#00b7ee")
                option.dividerColor = Color.parseColor("#00b7ee")
                option.cancelTextColor = Color.parseColor("#00b7ee")
                option.confirmTextColor = Color.parseColor("#00b7ee")
                StarDialog.Builder()
//                    .setTitle("我是标题")
                    .setContent("请注意这张是假币")
                    .setOptionSetting(option)
                    .setOnStarDialogClickListener(object : StarDialogClickListener {
                        override fun onConfirm() =
                            Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                        override fun onCancel() {
                            Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .show(supportFragmentManager)
            }
```
