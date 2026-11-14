#version 450 core

in vec3 position;
in vec2 texCoords;
in float normal;

out vec2 pass_texCoords;
out float pass_normal;

void main(void){
	gl_Position = vec4(position, 1.0);
	pass_texCoords = texCoords;
	pass_normal = normal;
}