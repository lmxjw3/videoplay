# videoplay

![](https://img.shields.io/badge/version-1.0.1-brightgreen.svg)&#160;
![](https://img.shields.io/badge/license-Apache%202-blue.svg)

[**English**](https://github.com/lmxjw3/videoplay/blob/master/README_EN.md)

RecyclerView 实现仿抖音上下滑动切换视频效果，已有线上项目，稳定可用。


### Import
```
implementation 'com.lmxjw3.media:videoplay:1.0.1'
```


### Usage

##### 1. XML 中引用 VideoPlayRecyclerView。
```
<com.lmx.library.media.VideoPlayRecyclerView
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```

##### 2. 把 VideoPlayRecyclerView 当普通 RecyclerView 用，并设置 Adapter 继承 VideoPlayAdapter。
```
public class MyVideoAdapter extends VideoPlayAdapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onPageSelected(int itemPosition, View itemView) {
        // 监听页面切换
    }
}
```
