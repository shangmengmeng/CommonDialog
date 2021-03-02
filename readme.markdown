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
//普通
            R.id.btn_dialog_normal -> {
                StarDialog(this)
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //带标题
            R.id.btn_dialog_title -> {
                StarDialog(this)
                        .setTitle("我带标题")
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //单按钮
            R.id.btn_dialog_single -> {
                StarDialog(this)
                        .single(true)
                        .setTitle("我带标题")
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
```

 **StarDialog的自定义使用方法**

| 方法                                  | 是否必须 | 描述     |
|-------------------------------------|------|--------|
| setTitle(title: String)             | 否    | 设置标题   |
| setContent(content: String)         | 是    | 设置内容   |
| setConfirmText(confirmText: String) | 否    | 设置确定文字 |
| setCancelText(cancelText: String)   | 否    | 设置取消文字 |
| single(isSingleButton: Boolean)     | 否    | 设置单按钮 |
| setAnimStyle(styleId: Int)     | 否    | 设置动画style |
| setOnStarDialogClickListener(dialogClickListener: StarDialogClickListener)     | 否    | 设置监听 |
| setOptionSetting(option: DialogSettingOption)     | 否    | 设置其它配置项 |

| 方法                          | 是否必须 | 描述     |
|-----------------------------|------|--------|
| setTitleColor(color: Int)   | 否    | 设置标题颜色 |
| setTitleSize(size: Float)   | 否    | 标题大小   |
| setContentColor(color: Int) | 否    | 内容颜色   |
| setContentSize(size: Float) | 否    | 内容大小   |
| setConfirmTextColor(color: Int) | 否    | 确认颜色   |
| setConfirmTextSize(size: Float) | 否    | 确认大小   |
| setCancelTextColor(color: Int) | 否    | 取消颜色   |
| setCancelTextSize(size: Float) | 否    | 取消大小   |
| setDividerColor(color: Int) | 否    | 分割线颜色  |


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

