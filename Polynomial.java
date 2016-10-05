// Name: Yunxi Liu
// USC loginid: yunxiliu
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
    	
    	this.termList = new ArrayList<Term>();
    	assert this.isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
    	
    	this.termList = new ArrayList<Term>();
    	if(term.getCoeff() != 0){
    		this.termList.add(term);
    	}
    	assert this.isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
    	
    	assert b.isValidPolynomial();  // to assert that poly b is valid
    	Polynomial sum = new Polynomial();
    	int p = 0, q = 0; //two pointers to record the location of terms in two term lists
    	
    	//add terms to the new sum
    	while(p < this.termList.size() && q < b.termList.size()){
    		
    		//choose the term with higher exponent
    		if(this.termList.get(p).getExpon() > b.termList.get(q).getExpon()){
    			sum.termList.add(this.termList.get(p));
    			p++;
    		}
    		else if(this.termList.get(p).getExpon() == b.termList.get(q).getExpon()){
    			double newCoeff = this.termList.get(p).getCoeff() + b.termList.get(q).getCoeff();
    			if(newCoeff != 0){
    				sum.termList.add(new Term(newCoeff, this.termList.get(p).getExpon()));
    			}
    			p++;
				q++;
    		}
    		else{
    			sum.termList.add(b.termList.get(q));
    			q++;
    		}
    	}
    	
    	//the situation where all terms in this list have been added to the sum
    	if(p == this.termList.size()){
			while(q < b.termList.size()){
				sum.termList.add(b.termList.get(q));
				q++;
			}
		}
    	
    	//the situation where all terms in b list have been added to the sum
    	else if(q == b.termList.size()){
    		while(p < this.termList.size()){
    			if(this.termList.get(p).getCoeff() == 0){
	    			p++;
	    			continue;
	    		}
    			sum.termList.add(this.termList.get(p));
    			p++;
    		}
    	}
    	assert this.isValidPolynomial();   // assert that this poly is valid
    	assert sum.isValidPolynomial();    // assert that the sum poly is valid
    	return sum;  
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
    	double eval = 0;
    	for(Term term: termList){
    		eval += term.getCoeff() * Math.pow(x, term.getExpon());
    	}
    	return eval;
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
    	
    	StringBuilder sb = new StringBuilder();
    	int i;
    	
    	//empty termList
    	if(this.termList.size() == 0){
    		return "0.0";
    	}
    	
    	//deal with situation where coefficient is equal to +/- 1 or exponent is equal to 1
    	for(i = 0; i < this.termList.size() - 1; i++){
    		if(this.termList.get(i).getCoeff() == 1 && this.termList.get(i).getExpon() == 1){
    			sb.append("x + ");
    		}
    		else if(this.termList.get(i).getCoeff() == -1 && this.termList.get(i).getExpon() == 1){
    			sb.append("-x + ");
    		}
    		else if(this.termList.get(i).getExpon() == 1){
    			sb.append(this.termList.get(i).getCoeff() + "x + ");
    		}
    		else if(this.termList.get(i).getCoeff() == 1){
    			sb.append("x^" + this.termList.get(i).getExpon() + " + ");
    		}
    		else if(this.termList.get(i).getCoeff() == -1){
    			sb.append("-x^" + this.termList.get(i).getExpon() + " + ");
    		}
    		else{
    			sb.append(this.termList.get(i).getCoeff() + "x^" + this.termList.get(i).getExpon() + " + ");
    		}
    	}
    	
    	//deal with the situation where the term is a constant
    	if(this.termList.get(i).getExpon() == 0){
    		sb.append(this.termList.get(i).getCoeff());
    	}
    	
    	//deal with the last term
    	else if(this.termList.get(i).getCoeff() == 1 && this.termList.get(i).getExpon() == 1){
			sb.append("x");
		}
    	
    	else if(this.termList.get(i).getCoeff() == -1 && this.termList.get(i).getExpon() == 1){
			sb.append("-x");
		}
		else if(this.termList.get(i).getExpon() == 1){
			sb.append(this.termList.get(i).getCoeff() + "x");
		}
    	
		else if(this.termList.get(i).getCoeff() == 1){
			sb.append("x^" + this.termList.get(i).getExpon());
		}
    	
    	else{
    		sb.append(this.termList.get(i).getCoeff() + "x^" + this.termList.get(i).getExpon());
    	}
    	
    	return sb.toString();
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true if the poly data is in a valid state.
    */
    private boolean isValidPolynomial() {
    	
    	for(int i = 0; i < termList.size() - 1; i++){
    		//to check the exponent
    		if(termList.get(i).getExpon() < 0){
    			return false;
    		}
    		
    		//to check the order of terms
    		if(termList.get(i).getExpon() < termList.get(i + 1).getExpon()){
    			return false;
    		}
    		
    		//to check there is no term with 0 coefficient
    		if(termList.get(i).getCoeff() == 0){
    			return false;
    		}
    	}
    	
    	//to check the last term
    	if(termList.size() > 0){
    		if(termList.get(termList.size() - 1).getExpon() < 0 || termList.get(termList.size() - 1).getCoeff() == 0)
    			return false;
		}
		
    	return true;
    }
    
    /**
     * Representation invariant:
     * 1. The exponent of all terms should be non-negative;
     * 2. Exponent of terms in ArrayList should be in decreasing order;
     * 3. The ArrayList does not contain two terms with same exponent;
     * 4. The ArrayList does not contain a term with zero coefficient, such as the term(0, 2).
    */
    
    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)
    private ArrayList<Term> termList; //to store all terms in the polynomial
    
}