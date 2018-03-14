export ANDROID_HOME=$ANDROID_SDK
export JAVA_HOME=$JDK7
export GRADLE_HOME=/data/rdm/apps/gradle/gradle-2.2.1
export PATH=$JDK7/bin:$GRADLE_HOME/bin:$PATH

## package begin
set -x

PACKAGE_NAME=IM_Android_SDK_${MajorVersion}.${MinorVersion}.${FixVersion}
PACKAGE_PATH=bin/${PACKAGE_NAME}

mkdir -p ${PACKAGE_PATH}/samples/sample/
cp -rf app presentation sdk tlslibrary ui ${PACKAGE_PATH}/samples/sample/
cp -rf gradle* settings.gradle ${PACKAGE_PATH}/samples/sample/

cp -rf doc/build.gradle ${PACKAGE_PATH}/samples/sample/

mkdir -p ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/bugly_1.3.0_imsdk_release.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/imsdk.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/mobilepb.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/qalsdk.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/tls_sdk.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/wup-1.0.0-SNAPSHOT.jar ${PACKAGE_PATH}/libs/
cp -rf sdk/libs/soload.jar ${PACKAGE_PATH}/libs/

cp -rf sdk/src/main/jniLibs/* ${PACKAGE_PATH}/libs/

mkdir -p ${PACKAGE_PATH}/docs/
cp -rf doc/package/* ${PACKAGE_PATH}/

pushd bin/
zip -r "${PACKAGE_NAME}.zip" "${PACKAGE_NAME}"
rm -rf "${PACKAGE_NAME}"
popd

set +x
## package end

gradle build
cp app/build/outputs/apk/app-debug.apk bin/app-debug_${MajorVersion}.${MinorVersion}.${FixVersion}.apk
cp app/build/outputs/apk/app-release.apk bin/app-release_${MajorVersion}.${MinorVersion}.${FixVersion}.apk
