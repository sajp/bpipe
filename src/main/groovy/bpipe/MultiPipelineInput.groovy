package bpipe

import java.util.Iterator;
import groovy.util.logging.Log;

/**
 * Extends {@link PipelineInput} to handle multiple inputs.
 * <p>
 * Where {@link PipelineInput} tries to always return just a single
 * input that matches what the user expects, {@link MultiPipelineInput} 
 * resolves all available inputs that match what the user expects and returns
 * them separated by spaces.
 * 
 * @author ssadedin
 */
@Log
class MultiPipelineInput extends PipelineInput implements Iterable {
	
    MultiPipelineInput(def input, List<PipelineStage> stages) {
		super(input,stages)	
	}
	
	/**
	 * Maps to all of the values separated by spaces
	 */
    @Override
	public String mapToCommandValue(def values) {
		def result = Utils.box(values)
        result.each { this.resolvedInputs << String.valueOf(it) }   
        return result.collect { String.valueOf(it) }.join(' ')
	}

	String toString() {
       return Utils.box(super.@input).join(" ")
    }

	@Override
	public Iterator iterator() {
		return Utils.box(super.@input).listIterator()
	}
}