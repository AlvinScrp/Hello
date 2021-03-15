package com.a.smalidex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import org.jf.dexlib2.AccessFlags;
//import org.jf.dexlib2.DexFileFactory;
//import org.jf.dexlib2.Opcodes;
//import org.jf.dexlib2.dexbacked.DexBackedClassDef;
//import org.jf.dexlib2.dexbacked.DexBackedDexFile;
//import org.jf.dexlib2.iface.ClassDef;
//import org.jf.dexlib2.iface.DexFile;
//import org.jf.dexlib2.immutable.ImmutableClassDef;
//import org.jf.dexlib2.immutable.ImmutableDexFile;
//import org.jf.dexlib2.rewriter.ClassDefRewriter;
//import org.jf.dexlib2.rewriter.DexRewriter;
//import org.jf.dexlib2.rewriter.Rewriter;
//import org.jf.dexlib2.rewriter.RewriterModule;
//import org.jf.dexlib2.rewriter.Rewriters;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.AbstractSet;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

        String className = "com.a.Hack";
        try {
            Class clz=Class.forName(className);
          Object obj=  clz.newInstance();
            tv.setText(clz.getName()+","+obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.getMessage());

        }
//        try {
//            String dexPath = copyAssetsDex(this, "patch.dex");
//            String injectClassName = "Lcom/a/Hack;";
//            injectClass(this, dexPath, injectClassName);
//
//            DexBackedDexFile dexFile = DexFileFactory.loadDexFile(getInjectPath(this), Opcodes.getDefault());
//            for (DexBackedClassDef aClass : dexFile.getClasses()) {
//                Log.d("alvin", aClass.getType());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    private static String getInjectPath(Context context) {
        return context.getExternalCacheDir().getAbsolutePath() + "/patchinject.dex";
    }

//    private static void injectClass(Context context, String dexPath, String injectClassName) throws Exception {
//        DexBackedDexFile dexFile = DexFileFactory.loadDexFile(dexPath, Opcodes.getDefault());
//        ImmutableDexFile immutableDexFile = ImmutableDexFile.of(dexFile);
//
//        Set<ClassDef> classDefs = new HashSet<>();
//        for (ImmutableClassDef classDef : immutableDexFile.getClasses()) {
//            classDefs.add(classDef);
//        }
//        ImmutableClassDef immutableClassDef = new ImmutableClassDef(
//                injectClassName,
//                AccessFlags.PUBLIC.getValue(),
//                "Ljava/lang/Object;",
//                null, null, null, null, null);
//        classDefs.add(immutableClassDef);
//
//        String patchInjectPath = getInjectPath(context);
//        File file = new File(patchInjectPath);
//        if (file != null && file.exists()) file.delete();
//        DexFileFactory.writeDexFile(patchInjectPath, new DexFile() {
//            @Nonnull
//            @Override
//            public Set<ClassDef> getClasses() {
//                return new HashSet<>(classDefs);
//            }
//
//            @Nonnull
//            @Override
//            public Opcodes getOpcodes() {
//                return dexFile.getOpcodes();
//            }
//        });
//    }
//
//    private static String copyAssetsDex(Context context, String dexFileName) {
//        String hackPath = context.getExternalCacheDir().getAbsolutePath() + "/" + dexFileName;
//        File destFile = new File(hackPath);
//        if (destFile.exists()) {
//            destFile.delete();
//        }
//        try {
//            InputStream is = context.getAssets().open(dexFileName);
//            FileOutputStream fos = new FileOutputStream(destFile);
//            byte[] buffer = new byte[1024];
//            int byteCount;
//            while ((byteCount = is.read(buffer)) != -1) {
//                fos.write(buffer, 0, byteCount);
//            }
//            fos.flush();
//            is.close();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return destFile.getAbsolutePath();
//
//
//    }
}