
## SwipeCardView

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SwipeCardView-lightgrey.svg?style=flat)](http://swipecard.osslab.online/) [![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html) [![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14) [![OSSLab](https://img.shields.io/badge/OSSLab-开源软件实验室-blue.svg?style=flat)](http://osslab.online/)

支持左右滑动操作的卡牌效果，可以实现类似 Tinder、花田、明星空间等应用的喜欢、不喜欢 以及 关注功能，同时支持查看卡牌详细资料。

通过异步加载提高数据处理性能，并使用相同的布局参数作为框架布局（你可以在 xml 文件中使用 `android:layout_gravity` 定义布局样式）。

<img src="./preview/preview.gif" alt="SwipeCardView" title="SwipeCardView" width="300" height="447" align="right" vspace="52" />


## 使用方法

首先，你需要导入模块项目或者添加依赖类库：

```groovy
dependencies {
    compile 'online.osslab:SwipeCard:1.0.0'
}
```

#### 核心用法：AdapterView

```java

swipeCardView = (SwipeCardView) findViewById(R.id.swipeCardView);
//swipeCardView.setIsNeedSwipe(true); // 是否开启swipe滑动效果，当不调用此方法设置时，默认开启;
swipeCardView.setSwipeListener(this);
swipeCardView.setOnItemClickListener(this);

```

#### 样式文件：XML

```xml
<online.osslab.SwipeCardView
        android:id="@+id/swipeCardView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        swipe:min_adapter_stack="4"
        swipe:max_visible="4"
        swipe:vertical_offset="28dp"/>

```

#### 回调方法

- **onSwipeListener**

```java
    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        // 向左滑动
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        // 向右滑动
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

```

- **OnItemClickListener**

``` java
    @Override
    public void onItemClicked(MotionEvent event, View view, Object dataObject) {
        if (view.getTag() instanceof ViewHolder) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            ViewHolder vh = (ViewHolder) v.getTag();
            View child = vh.portraitView;
            Rect outRect = new Rect();
            child.getGlobalVisibleRect(outRect);
            if (outRect.contains(x, y)) {
                // 查看详情
            } else {
                outRect.setEmpty();
                child = vh.collectView;
                child.getGlobalVisibleRect(outRect);
                if (outRect.contains(x, y)) {
                    // 关注明星
                }
            }
        }
    }
```


## 关于作者

- [wall-e@live.cn](mailto:wall-e@live.cn)
- [开源软件实验室](http://osslab.online/)


## 许可协议

    Copyright 2014 Dionysis Lorentzos

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

