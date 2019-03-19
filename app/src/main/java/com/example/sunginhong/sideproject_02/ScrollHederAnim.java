package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.view.View;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Animation;

public class ScrollHederAnim {
    Context context;

    public ScrollHederAnim() {

    }

    public static void HeaderShow(View item, int HeaderPosY1, int HeaderPosY2, int duration) {
        Utils_Animation.TransAnim(item, 0, 0, HeaderPosY1, HeaderPosY2, duration );
    }
    public static void HeaderHide(View item, int HeaderPosY1, int HeaderPosY2, int duration) {
        Utils_Animation.TransAnim(item, 0, 0, HeaderPosY1, HeaderPosY2, duration );
    }
}