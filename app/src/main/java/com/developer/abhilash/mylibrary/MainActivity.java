package com.developer.abhilash.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> contentsAdapter;
    String[] contents={"Acid","Base","Salt","Chemical Reactions"};
    ListView contentsListView;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDatabase();

        contentsListView=(ListView)findViewById(R.id.contents_listView);
        contentsAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_list_item);
        for(int i=0;i<4;i++)
            contentsAdapter.add(contents[i]);
        contentsListView.setAdapter(contentsAdapter);

        contentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int chapter_no=0;
                switch(position){
                    case 0:
                        chapter_no=0;
                        break;
                    case 1:
                        chapter_no=1;
                        break;
                    case 2:
                        chapter_no=2;
                        break;
                    case 3:
                        chapter_no=3;
                        break;
                    default:
                        break;
                }
                Intent i=new Intent(MainActivity.this,References.class);
                i.putExtra("chapter_no",chapter_no);
                startActivity(i);
            }
        });
    }

    public void loadDatabase(){
        deleteDatabase("mylibrary");
        db=openOrCreateDatabase("mylibrary", Context.MODE_WORLD_WRITEABLE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS books (book_id int(5) primary key not null,book_name varchar(30),author varchar(30),edition int(5));");
        db.execSQL("INSERT INTO books ('book_id', 'book_name', 'author', 'edition') VALUES" +
                "(1, 'NCERT Chemistry Book', 'NCERT Author', 1)," +
                "(2, 'S.Chand''s  Chemistry', 'S.Chand', 2)," +
                "(3, 'ABC in Chemistry', 'author 2', 2)," +
                "(4, 'Applied Chemistry', 'author 3', 4);");

        db.execSQL("CREATE TABLE IF NOT EXISTS chapters (" +
                "  id int(11) PRIMARY KEY NOT NULL," +
                "  book_id int(5) NOT NULL," +
                "  chapter_no int(5) NOT NULL," +
                "  chapter_name varchar(30) NOT NULL," +
                "  chapter_content longtext NOT NULL" +
                ")");
        db.execSQL("INSERT INTO chapters (id, book_id, chapter_no, chapter_name, chapter_content) VALUES" +
                "(1, 1, 1, 'Acid', 'An acid is a molecule or ion capable of donating a hydrogen (proton or hydrogen ion H+), or, alternatively, capable of forming a covalent bond with an electron pair (a Lewis acid).The first category of acids is the proton donors or Bronsted acids. In the special case of aqueous solutions, proton donors form the hydronium ion H3O+ and are known as Arrhenius acids. Bronsted and Lowry generalized the Arrhenius theory to include non-aqueous solvents. A Bronsted or Arrhenius acid usually contains a hydrogen atom bonded to a chemical structure that is still energetically favorable after loss of H+.')," +
                "(2, 1, 2, 'Base', 'In chemistry, bases are substances that, in aqueous solution, are slippery to the touch, taste astringent, change the color of indicators (e.g., turn red litmus paper blue), react with acids to form salts, promote certain chemical reactions (base catalysis), accept protons from any proton donor, and/or contain completely or partially displaceable OH? ions. Examples of bases are the hydroxides of the alkali metals and alkaline earth metals (NaOH, Ca(OH)2, etc.).')," +
                "(3, 1, 3, 'Salt', 'In chemistry, a salt is an ionic compound that results from the neutralization reaction of an acid and a base.[1] Salts are composed of related numbers of cations (positively charged ions) and anions (negative ions) so that the product is electrically neutral (without a net charge). These component ions can be inorganic, such as chloride (Cl?), or organic, such as acetate (CH3CO2); and can be monatomic, such as fluoride , or polyatomic, such as sulfate.There are several varieties of salts. Salts that hydrolyze to produce hydroxide ions when dissolved in water are basic salts, whilst those that hydrolyze to produce hydronium ions in water are acidic salts. Neutral salts are those that are neither acid nor basic salts. Zwitterions contain an anionic centre and a cationic centre in the same molecule, but are not considered to be salts. Examples of zwitterions include amino acids, many metabolites, peptides, and proteins.')," +
                "(4, 1, 4, 'Chemical Reactions', 'A chemical reaction is a process that leads to the transformation of one set of chemical substances to another.[1] Classically, chemical reactions encompass changes that only involve the positions of electrons in the forming and breaking of chemical bonds between atoms, with no change to the nuclei (no change to the elements present), and can often be described by a chemical equation. Nuclear chemistry is a sub-discipline of chemistry that involves the chemical reactions of unstable and radioactive elements where both electronic and nuclear changes may occur.')," +
                "(5, 2, 1, 'Acid', 'Aqueous Arrhenius acids have characteristic properties which provide a practical description of an acid.[2] Acids form aqueous solutions with a sour taste, can turn blue litmus red, and react with bases and certain metals (like calcium) to form salts. The word acid is derived from the Latin acidus/acere meaning sour.[3] An aqueous solution of an acid has a pH less than 7 and is colloquially also referred to as ''acid'' (as in ''dissolved in acid''), while the strict definition[1] refers only to the solute. A lower pH means a higher acidity, and thus a higher concentration of positive hydrogen ions in the solution. Chemicals or substances having the property of an acid are said to be acidic.')," +
                "(10, 2, 2, 'Base', 'These particular substances produce hydroxide ions (OH-) in aqueous solutions, and are thus classified as Arrhenius bases. For a substance to be classified as an Arrhenius base, it must produce hydroxide ions in an aqueous solution. In order to do so, Arrhenius believed the base must contain hydroxide in the formula. This makes the Arrhenius model limited, as it cannot explain the basic properties of aqueous solutions of ammonia (NH3) or its organic derivatives (amines).[1] There are also bases that do not contain a hydroxide ion but nevertheless react with water, resulting in an increase in the concentration of the hydroxide ion.[2] An example of this is the reaction between ammonia and water to produce ammonium and hydroxide.[2] In this reaction ammonia is the base because it accepts a proton from the water molecule.[2] Ammonia and other bases similar to it usually have the ability to form a bond with a proton due to the unshared pair of electrons that they possess.')," +
                "(11, 2, 3, 'Salt', 'There are several varieties of salts. Salts that hydrolyze to produce hydroxide ions when dissolved in water are basic salts, whilst those that hydrolyze to produce hydronium ions in water are acidic salts. Neutral salts are those that are neither acid nor basic salts. Zwitterions contain an anionic centre and a cationic centre in the same molecule, but are not considered to be salts. Examples of zwitterions include amino acids, many metabolites, peptides, and proteins.')," +
                "(12, 2, 4, 'Chemical Reactions', 'The substance (or substances) initially involved in a chemical reaction are called reactants or reagents. Chemical reactions are usually characterized by a chemical change, and they yield one or more products, which usually have properties different from the reactants. Reactions often consist of a sequence of individual sub-steps, the so-called elementary reactions, and the information on the precise course of action is part of the reaction mechanism. Chemical reactions are described with chemical equations, which symbolically present the starting materials, end products, and sometimes intermediate products and reaction conditions.')," +
                "(13, 3, 1, 'Acid', 'Common aqueous acids include hydrochloric acid (a solution of hydrogen chloride which is found in gastric acid in the stomach and activates digestive enzymes), acetic acid (vinegar is a dilute aqueous solution of this liquid), sulfuric acid (used in car batteries), and citric acid (found in citrus fruits). As these examples show, acids (in the colloquial sense) can be solutions or pure substances, and can be derived from acids (in the strict[1] sense) that are solids, liquids, or gases. Strong acids and some concentrated weak acids are corrosive, but there are exceptions such as carboranes and boric acid.')," +
                "(14, 3, 2, 'Base', 'In water, by altering the autoionization equilibrium, bases yield solutions in which the hydrogen ion activity is lower than it is in pure water, i.e., the water has a pH higher than 7.0 at standard conditions. A soluble base is called an alkali if it contains and releases OH- ions quantitatively. However, it is important to realize that basicity is not the same as alkalinity. Metal oxides, hydroxides, and especially alkoxides are basic, and counteranions of weak acids are weak bases.')," +
                "(15, 3, 3, 'Salt', 'Usually, non-dissolved salts at standard temperature and pressure are solid, but there are exceptions (see molten salts and ionic liquids).Molten salts and solutions containing dissolved salts (e.g., sodium chloride in water) are called electrolytes, as they are able to conduct electricity. As observed in the cytoplasm of cells, in blood, urine, plant saps and mineral waters, mixtures of many different ions in solution usually do not form defined salts after evaporation of the water. Therefore, their salt content is given for the respective ions.')," +
                "(16, 3, 4, 'Chemical Reactions', 'Usually, non-dissolved salts at standard temperature and pressure are solid, but there are exceptions (see molten salts and ionic liquids).Molten salts and solutions containing dissolved salts (e.g., sodium chloride in water) are called electrolytes, as they are able to conduct electricity. As observed in the cytoplasm of cells, in blood, urine, plant saps and mineral waters, mixtures of many different ions in solution usually do not form defined salts after evaporation of the water. Therefore, their salt content is given for the respective ions.')," +
                "(17, 4, 1, 'Acid', 'The second category of acids are Lewis acids, which form a covalent bond with an electron pair. An example is boron trifluoride (BF3), whose boron atom has a vacant orbital which can form a covalent bond by sharing a lone pair of electrons on an atom in a base, for example the nitrogen atom in ammonia (NH3). Lewis considered this as a generalization of the BrÃ¸nsted definition, so that an acid is a chemical species that accepts electron pairs either directly or by releasing protons (H+) into the solution, which then accept electron pairs. However, hydrogen chloride, acetic acid, and most other Bronsted-Lowry acids cannot form a covalent bond with an electron pair and are therefore not Lewis acids. Conversely, many Lewis acids are not Arrhenius or Bronsted-Lowry acids. In modern terminology, an acid is implicitly a Bronsted acid and not a Lewis acid, since chemists almost always refer to a Lewis acid explicitly as a Lewis acid.')," +
                "(18, 4, 2, 'Base', 'Bases can be thought of as the chemical opposite of acids. However, some strong acids are able to act as bases.[4] Bases and acids are seen as opposites because the effect of an acid is to increase the hydroxonium (H3O+) concentration in water, whereas bases reduce this concentration. A reaction between an acid and base is called neutralization. In a neutralization reaction, an aqueous solution of a base reacts with an aqueous solution of an acid to produce a solution of water and salt in which the salt separates into its component ions. If the aqueous solution is saturated with a given salt solute, any additional such salt precipitates out of the solution.')," +
                "(19, 4, 3, 'Salt', 'Many ionic compounds can be dissolved in water or other similar solvents. The exact combination of ions involved makes each compound have a unique solubility in any solvent. The solubility is dependent on how well each ion interacts with the solvent, so there are certain patterns. For example, all salts of sodium, potassium and ammonium are soluble in water, as are all nitrates and many sulfates – barium sulfate, calcium sulfate (sparingly soluble) and lead(II) sulfate are examples of exceptions. However, ions that bind tightly to each other and form highly stable lattices are less soluble, because it is harder for these structures to break apart for the compounds to dissolve. For example, most carbonate salts are not soluble in water, such as lead carbonate and barium carbonate. Some soluble carbonate salts are: sodium carbonate, potassium carbonate and ammonium carbonate.')," +
                "(20, 4, 4, 'Chemical Reactions', 'Chemical reactions happen at a characteristic reaction rate at a given temperature and chemical concentration. Typically, reaction rates increase with increasing temperature because there is more thermal energy available to reach the activation energy necessary for breaking bonds between atoms.Reactions may proceed in the forward or reverse direction until they go to completion or reach equilibrium. Reactions that proceed in the forward direction to approach equilibrium are often described as spontaneous, requiring no input of free energy to go forward. Non-spontaneous reactions require input of free energy to go forward (examples include charging a battery by applying an external electrical power source, or photosynthesis driven by absorption of electromagnetic radiation in the form of sunlight).');");
        /*Cursor c=db.rawQuery("SELECT * FROM books",null);
        while(c.moveToNext()){
            Toast.makeText(MainActivity.this, c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3), Toast.LENGTH_SHORT).show();
        }
        c=db.rawQuery("SELECT * FROM chapters",null);
        while(c.moveToNext()){
            Toast.makeText(MainActivity.this,c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3)+" "+c.getString(4) , Toast.LENGTH_SHORT).show();
        }*/
    }

}
