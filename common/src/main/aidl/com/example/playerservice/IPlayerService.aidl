// aidl.aidl
package com.example.playerservice;

// Declare any non-default types here with import statements


interface IPlayerService {

    void rquestPlaySong(int id,String ids);
    void pause();
    void start();
    void seek(int time);

}
