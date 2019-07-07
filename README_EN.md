# videoplay

![](https://img.shields.io/badge/version-1.0.0-brightgreen.svg)&#160;
![](https://img.shields.io/badge/license-Apache%202-blue.svg)

[**中文版**](https://github.com/lmxjw3/videoplay/blob/master/README.md)

Slide up and down to switch video play by RecyclerView


### Import
```
implementation 'com.lmxjw3.media:videoplay:1.0.0'
```


### Usage

##### 1. Define in your xml.
```
<com.lmx.library.media.VideoPlayRecyclerView
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```

##### 2. Use VideoPlayRecyclerView as a normal RecyclerView , set an adapter extend VideoPlayAdapter to the VideoPlayRecyclerView.
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
        // watch on page select here
    }
}
```