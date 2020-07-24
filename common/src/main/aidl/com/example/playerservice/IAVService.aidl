// IAVService.aidl
package com.example.playerservice;

// Declare any non-default types here with import statements

interface IAVService {

  void playedTime(int time);
  void lyricChange(int item);
  void lyric(in Map lyric);
  void playList(in int[] ids);
  void pause(int id);
  void started(int id);
}
