// IAVService.aidl
package com.example.common;
//import com.example.common.SongsDetail;
import com.example.common.entity.SongDetail;


interface IAVService {

  void playedTime(int time);
  void lyricChange(int item);
  void lyric(in Map lyric);
  void playList(in int[] ids);
  void pause(int id);
  void started(int id);
  void playTime(int time);
  void currentSongDetail(in SongDetail songDetail);

}
