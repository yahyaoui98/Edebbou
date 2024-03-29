/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;


import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Product2;
import com.mycompany.myapp.services.PanierService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple details form
 *
 * @author Shai Almog
 */
public class panier extends com.codename1.ui.Form {
  public static final String ACCOUNT_SID = "ACe03f2ebcf6df3b605cd29841abfd2910";
  public static final String AUTH_TOKEN = "cb809c54635309a8654c31d044858c6a";
  int i=0;
    public panier() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public panier(com.codename1.ui.util.Resources resourceObjectInstance) {
        setToolbar(new Toolbar(true));
        setTitle("Panier");
        
        Container hi2 = new Container(BoxLayout.y());
        hi2.add(new Label(" "));
        hi2.add(new Label(" "));
        hi2.add(new Label(" "));
            PanierService pss = new PanierService();
          
            if (PanierService.panier==null)
            {
               
                i=1;
             }
            else  {
                
                 for (Map.Entry<Product2, Integer> entry : PanierService.panier.entrySet()) {
                        if (i!=1)
                    {
                         EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                Image image = URLImage.createToStorage(placeholder, entry.getKey().getImage(), "http://localhost/backt/productimage/" + entry.getKey().getImage(), URLImage.RESIZE_SCALE_TO_FILL);
                Container imgC = new Container();
          imgC.add(image);
                MultiButton l = new MultiButton(""+entry.getKey().getNom());  
                l.setTextLine2("Quantité : "+entry.getValue().toString());
                l.setTextLine3("Prix : "+entry.getKey().getPrix());
                l.setTextLine4("Total : "+(entry.getKey().getPrix()*entry.getValue()));
                
//Label l = new Label("'"+entry.getKey().getNom()+"'  Qte: '"+entry.getValue().toString()+"' P : '"++"' Tot : "+(entry.getKey().getPrix()*entry.getValue()));
            hi2.add(l);
            hi2.add(imgC);
            }
                Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                TextField tf = new TextField();
                tf.setPreferredW(200);
                
                MultiButton bt = new MultiButton("Valider");
                bt.addActionListener(lll->{
                    PanierService ps = new PanierService();
                    ps.modifProduit(entry.getKey(), Integer.parseInt(tf.getText()));
                    
                        System.out.println(i);
                    new panier(resourceObjectInstance).show();
                   
                });
               
                c.add(tf);
                c.add(bt);
                hi2.add(c);
                 
                  }
              } 
           
            if (i==0)
            {
            Label total = new Label("Total : "+pss.total());
            hi2.add(total);
            getToolbar().addCommandToLeftBar("Retour", null, ev -> {
                new productsForm(resourceObjectInstance).showBack();
            });
            Button checkout = new Button("checkout");
            checkout.addActionListener(l->{
            pss.Checkout();
                    Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json").
        queryParam("To", "+21653853529").
        queryParam("From", "+12074264959").
        queryParam("Body", "123").
        basicAuth(ACCOUNT_SID, AUTH_TOKEN).//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
        getAsJsonMap();
            new commandes(resourceObjectInstance).show();
            
            });
            
            hi2.add(checkout);
            
            }
            else {
                Label n= new Label("Votre panier est vide");
                hi2.add(n);
                getToolbar().addCommandToLeftBar("Retour", null, ev -> {
                new productsForm(resourceObjectInstance).showBack();
            });
            }
            add(hi2);
        //getToolbar().setBackCommand("", e -> last.show());
    }


}
