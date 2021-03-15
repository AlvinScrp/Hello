package com.a.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class QFixPlugin implements Plugin<Project> {

    void apply(Project project1) {
        System.out.println("========================");
        System.out.println("Hello gradle QFixPlugin!");
        System.out.println("========================");

        project1.afterEvaluate { project ->
            project.tasks.packageDebug {
                doLast {
                    println 'QFixPlugin inject Class after packageDebug'
                    // outputs: /Users/mawenqiang/Documents/demo_project/Hello/smalidex/build/intermediates/incremental/packageDebug/tmp
                    project.tasks.packageDebug.getOutputs().getFiles().collect().each { element ->
                        println "outputs: " + element
                    }
                }
            }
        }

        project1.afterEvaluate { project ->
            project.tasks.mergeProjectDexDebug {
                doLast {
                    println 'QFixPlugin inject Class after mergeProjectDexDebug'
                    project.tasks.mergeProjectDexDebug.getOutputs().getFiles().each { dir ->
                        println "outputs: " + dir
                        if (dir != null && dir.exists()) {
                            def files = dir.listFiles()
                            files.each { file ->
                                String dexfilepath = file.getAbsolutePath()
                                println "Outputs Dex file's path: " + dexfilepath
                                InjectClassHelper.injectClass(dexfilepath, "Lcom/a/Hack;")
                            }

                        }
                    }
                }
            }
        }
    }
}

