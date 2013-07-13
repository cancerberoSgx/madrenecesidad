//package org.sgx.madrenecesidad.client.ui;
//
//import org.sgx.yuigwt.yui.YUICallback;
//import org.sgx.yuigwt.yui.YuiContext;
//import org.sgx.yuigwt.yui.YuiLoader;
//import org.sgx.yuigwt.yui.dd.Drag;
//import org.sgx.yuigwt.yui.dd.DragConfig;
//import org.sgx.yuigwt.yui.resize.Resize;
//import org.sgx.yuigwt.yui.resize.ResizeConfig;
//import org.sgx.yuigwt.yui.util.JsObject;
//
//public class YuiTest {
//public static void main(String[] args) {
//	doYUIResizableDraggableStuff();
//	
//}
//private static void doYUIResizableDraggableStuff() {
//	String yuijs = "http://yui.yahooapis.com/3.10.3/build/yui/yui-min.js";
//	String[] modules={"resize", "resize-plugin", "dd-plugin"};
//	YUICallback callback = new YUICallback() {			
//		@Override
//		public void ready(YuiContext Y) {
//			ResizeConfig resizeConfig = ResizeConfig.create().node(".main-tools-panel"); 
//			Resize resize = Y.newResize(resizeConfig); 
//			
//			DragConfig ddConfig = DragConfig.create().node(".main-tools-panel");
//			Drag dd = Y.newDDDrag(ddConfig)
//					.addHandle(".tabs, .alert"); 
//////			
////			Y.all(".state-panel .tabs").plug(Y.Plugin().Drag()); 
//			
//			
//		}
//	};
//	YuiLoader.load(yuijs, modules, callback); 
//}
//}
