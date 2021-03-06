package cn.edu.zuoye1

import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {

    var second = 0
    var running = false

//    var time=""

    var clicklist = arrayOf(0, 0, 0, 0, 0, 0)

    var life = 10

    var successcount= 0

    var fankui = "A"
    var mediaPlayer = MediaPlayer()

    fun zhendong(){
        val vibrator = this.getSystemService(android.content.Context.VIBRATOR_SERVICE) as Vibrator
//        val mediaPlayer = MediaPlayer.create(this, R.raw.dianzhong)//msg是滴的MP3文件，很小
//        mediaPlayer.start()
        vibrator.vibrate(200)
//        object : Thread() {
//            override fun run() {
//                super.run()
//                mediaPlayer.start()
//            }
//        }.start()
//        object : Thread() {
//            override fun run() {
//                super.run()
//                vibrator.vibrate(200)
//            }
//        }.start()

    }//点击按钮震动
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        runTimer()


        buttonA.isEnabled=false
        buttonB1.isEnabled=false
        buttonB2.isEnabled=false
        buttonB3.isEnabled=false
        buttonB4.isEnabled=false
        buttonB5.isEnabled=false
        imageView2.isEnabled=false

        textView_successCount.text="成功：${successcount}"
        textView_life.text="生命：${life}"
        var bofang= true


         mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
        button_bgm.setOnClickListener {

            if (bofang){
                mediaPlayer.start()
                bofang =false
            }else{

               // mediaPlayer =  MediaPlayer.create(this, null)
                mediaPlayer.pause()
                Log.d("button_music", "music")
                bofang = true
            }


        }
        button_home.setOnClickListener {
//            mediaPlayer.reset()
//            mediaPlayer.release()
            finish()
        }

        button_start.setOnClickListener {
            zhendong()

            second = 0
            running = true

            Log.d("button_start", "开始游戏")
            buttonA.isEnabled=true
            buttonB1.isEnabled=true
            buttonB2.isEnabled=true
            buttonB3.isEnabled=true
            buttonB4.isEnabled=true
            buttonB5.isEnabled=true
            imageView2.isEnabled=true
            //timeEnd()
        }

        buttonA.setOnClickListener {
            zhendong()
            if(fankui=="A" && clicklist[0]==0){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }
            else if(fankui=="B1" && clicklist[0]==1){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }
            else if(fankui=="B2" && clicklist[0]==2){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }
            else if(fankui=="B3" && clicklist[0]==3){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }
            else if(fankui=="B4" && clicklist[0]==4){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }
            else if(fankui=="B5" && clicklist[0]==5){
                clicklist[0]++
                Log.d("buttonA", "${clicklist[0]}")
            }else{
                reclicklist()
                clickError()
            }

            isLife(life)
        }

        buttonB1.setOnClickListener {
            zhendong()

            if (clicklist[0] == 1) {
                clicklist[1]++
                fankui = "B1"
                Log.d("buttonB1", "${clicklist[1]}")
            }
            else {
                reclicklist()
                clickError()
            }

            isLife(life)
        }
        buttonB2.setOnClickListener {
            zhendong()
            if (clicklist[0] == 2) {
                clicklist[2]++
                fankui = "B2"
                Log.d("buttonB2", "${clicklist[2]}")
            } else {
                reclicklist()
                clickError()

            }

            isLife(life)

        }
        buttonB3.setOnClickListener {
            zhendong()
            if (clicklist[0] == 3) {
                clicklist[3]++
                fankui = "B3"
                Log.d("buttonB3", "${clicklist[3]}")
            } else {
                reclicklist()
                clickError()

            }
            isLife(life)
        }
        buttonB4.setOnClickListener {
            zhendong()

            if (clicklist[0] == 4) {
                clicklist[4]++
                fankui = "B4"
                Log.d("buttonB4", "${clicklist[4]}")
            } else {
                reclicklist()
                clickError()

            }
            isLife(life)
        }
        buttonB5.setOnClickListener {
            zhendong()
            if (clicklist[0] == 5) {
                clicklist[5]++
                fankui = "B5"
                Log.d("buttonB5", "${clicklist[5]}")
            } else {
                reclicklist()
                clickError()
            }
            isLife(life)
            isSuccess()
            Log.d("buttonsum", "${clicklist[0]} ${clicklist[1]} ${clicklist[2]} ${clicklist[3]} ${clicklist[4]} ${clicklist[5]}")
        }
            //点到手了
        imageView2.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.daoge)//msg是滴的MP3文件，很小
            mediaPlayer.start()

            reclicklist()
            clickError()

            isLife(life)

        }
    }



        fun reclicklist(){   //重置
            clicklist = arrayOf(0, 0, 0, 0, 0, 0)
            fankui="A"
        }


        fun isSuccess(){  //判端一轮成功
            if(clicklist[0]== 5 && clicklist[1]== 1 && clicklist[2]== 1 && clicklist[3]== 1 && clicklist[4]== 1 && clicklist[5]== 1 ){
                Toast.makeText(this, "成功啦！", Toast.LENGTH_SHORT).show()
                reclicklist()
                successcount++
                textView_successCount.text="成功：${successcount}"
                Log.d("buttoncount","${successcount}")
            }
        }
//         fun sucessCount(){
//
//         }
        fun isLife(l: Int){  //十次生命，失败跳失败界面
            if(l<=0){
                Toast.makeText(this, "你失败了，再来一局", Toast.LENGTH_SHORT).show()
                second = 0
                running = false
                life = 10
                successcount = 0

                val intent = Intent(this,FailActivity::class.java)
                startActivity(intent)
//
//                mediaPlayer.reset()
//                mediaPlayer.release()


                reclicklist()

            }

        }

        fun timeEnd(){   //时间到了,成功结束一局游戏
            if(second>30){
                Toast.makeText(this, "时间到了,在30秒内成功了${successcount}轮", Toast.LENGTH_SHORT).show()
                second = 0
                running=false
                //successcount = 0

                val intent = Intent(this,SuccessActivity::class.java)
                intent.putExtra("successcount", successcount)
                startActivity(intent)
//
//                mediaPlayer.reset()
//                mediaPlayer.release()


            }
        }


        fun clickError(){   //点错位置了
            if(life>=0) {
                life--
            }
            reclicklist()
            textView_life.text="生命：${life}"
//            Toast.makeText(this, "位置错误，请重新点击,你还有${life}次机会!!!", Toast.LENGTH_SHORT).show()
        }

        fun runTimer(){
            val handler = Handler()
            val runnable = object : Runnable{
                override fun run() {
                    val hours = second / 3600
                    val minutes = (second % 3600) / 60
                    val secs = second % 60
                    textView_time.text = String.format("%02d:%02d:%02d", hours, minutes, secs)

                    if (running) {
                        second++
                    }
                    timeEnd()
                    handler.postDelayed(this, 2000)
                }
            }
            handler.post(runnable)
        }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("buttondestory","销毁")
        mediaPlayer.reset()
        mediaPlayer.release()
    }

//    override fun onStop() {
//        super.onStop()
//        Log.d("buttonStop","停止")
//        mediaPlayer.pause()
//    }

    override fun onPause() {
        super.onPause()
        Log.d("buttonPause","暂停")
        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("buttonrestart","成功重启")
        reclicklist()
        life=10
        successcount=0
        //-------------------
        second = 0
        running = false

        mediaPlayer.start()
        textView_successCount.text="成功：${successcount}"
        textView_life.text="生命：${life}"
        buttonA.isEnabled=false
        buttonB1.isEnabled=false
        buttonB2.isEnabled=false
        buttonB3.isEnabled=false
        buttonB4.isEnabled=false
        buttonB5.isEnabled=false
        imageView2.isEnabled=false

    }

    }